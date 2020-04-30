package com.example.nationalflagquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int LANG_KOREAN = 0;
    private static final int LANG_ENGLISH = 1;
    private List<String> fileNameList;          // 국가파일명 저장
    private List<String> quizCountriesList;     // 퀴즈에 포함되는 국가명
    private Map<String, Boolean> regionsMap;    // 퀴즈에 포함되는 지역을 체크한 맵
    private Map<String, String> engkoMap;       // 영문 국가명과 한글 국가명을 매핑한 맵
    private int correctCount;                   // 맞춘 정답의 개수
    private int totalGuesses;                   // 사용자가 문제 풀기를 시도한 횟수
    private double random;
    private Handler handler;
    private Animation shakeAnimation;
    private String answer;                      // 정답 국가명
    private int guessRows;                      // 선택할 행의 수
    private int lang;                           // 언어 선택 저장(0: 한글, 1: 영어)

    // 위젯 변수 선언
    private TextView answerTextView;            // 정답, 오답 표시
    private TextView questionNumberTextView;    // 현재의 퀴즈 번호 표시
    private ImageView flagImageView;            // 국기 이미지 표시
    private TableLayout buttonTableLayout;      // 버튼을 표시하는 테이블 레이아웃 표시`

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameList = new ArrayList<String>();
        quizCountriesList = new ArrayList<String>();
        regionsMap = new HashMap<String, Boolean>();
        guessRows = 1;      // 선택할 답의 행의 갯수를 기본값 1로 설정
        handler = new Handler(); // 내가 원하는 것을 원할 때 실행할 수 있게끔 해줌
        // 애니메이션 파일을 읽어서 초기화
        shakeAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.incorrect_shake); //현재 mainactivity의 속성을 사용하겠다.
        shakeAnimation.setRepeatCount(3); // 애니메이션 3회 반복 설정
        //Random random = new Random();

        // 지역명의 배열을 생성
        String[] regionNames = getResources().getStringArray(R.array.regionsList);

        // 지역 선택 초기화: 처음에 전체 지역을 모두 포함
        for (String region : regionNames){
            System.out.println("1"+region);
            regionsMap.put(region, true);
        }

        // 위젯 요소에 대한 초기화
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        answerTextView = findViewById(R.id.answerTextView);
        flagImageView = findViewById(R.id.flagImageView);
        buttonTableLayout = findViewById(R.id.buttonTableLayout);

        questionNumberTextView.setText(getResources().getString(R.string.questionNumber));

        engkoMap = new HashMap<String, String>();
        String [] keys = getResources().getStringArray(R.array.nation_en_keys); // 영문 국가명을 키로 문자열 배열 생성
        String [] values = getResources().getStringArray(R.array.nation_ko_values); // 한글 국가명 배열

        for(int i = 0; i < keys.length; i++){
            engkoMap.put(keys[i], values[i]);
        }
        lang = LANG_KOREAN;

        startQuiz();
    }

    private void startQuiz(){
        // 국기 이미지 파일명을 수집하기 위해 AssetManager를 사용(안드로이드 제공)
        AssetManager assets = getAssets();
        fileNameList.clear(); // 국기 파일명 목록을 모두 지운다.

        try {
            Set<String> regions = regionsMap.keySet(); // 맵에서 지역 이름 목록을 가져온다.

            // region의 대륙들의 value 값을 가져옴
            for (String region : regions) {
                if (regionsMap.get(region)) {
                    //대륙 지역명의 선택이 true인 asset 목록을 읽어들인다.
                    String[] paths = assets.list(region);

                    for (String path : paths) {
                        //파일 확자자명을 제외한 파일명을 목록에 저장
                        fileNameList.add(path.replace(".png", ""));
                        System.out.println("2"+path);
                    } // end of for
                } // end of if
            } // end of for
            // 테스트용
//        for(int i=0; i<fileNameList.size(); i++){
//            System.out.println(fileNameList.get(i)+"");
//        }
        } catch(IOException e){
            Log.e(TAG, "이미지 파일명 로드 중 에러 발생", e);
        }

        correctCount = 0;
        totalGuesses = 0;
        quizCountriesList.clear();      // 퀴즈에 포함되는 국가명 목록

        int flagCount = 1;
        int numOfFlags = fileNameList.size();
        while(flagCount <= 10){
            int index = (int)(Math.random() * numOfFlags);      // 전체 국가의 숫자 중 무작위로 숫자를 추출

            // 중복된 숫자가 생성되는 것을 방지하기 위함
            String fileName = fileNameList.get(index);

            if(!quizCountriesList.contains(fileName)){
                quizCountriesList.add(fileName);
                flagCount++;
            } // end of if
        }// end of while

        loadNextFlag(); // 첫번째 국기를 표시하여 게임을 시작
    }

    // 문제 풀기 목록의 국기를 표시하여 게임을 실행
    private void loadNextFlag(){
        String nextImageName = quizCountriesList.remove(0);
        answer = nextImageName;      //국가명 정답을 갱신

        answerTextView.setText(""); // 정답 텍스트 뷰를 지운다.

        // 화면에 현재 문제의 번호를 표시
        questionNumberTextView.setText(getResources().getString(R.string.question) +
                "정답은 " + correctCount + "개");

        // nextImage 명에서 지역명을 추출 (assets에서 지역명 아래의 국기 이미지를 읽기 위함)
        String region = nextImageName.substring(0, nextImageName.indexOf("-"));

        AssetManager assets = getAssets();
        InputStream inStream;
        try {
            // assets 폴더에서 다음 국기 이미지를 읽기위한 입력 스트림 생성
            inStream = assets.open(region + "/" + nextImageName + ".png");

            // 입력 스트림에서 이미지 데이터(.png)를 읽어 Bitmap 객체로 변환.
            Bitmap flag = BitmapFactory.decodeStream(inStream);
            flagImageView.setImageBitmap(flag);
        } catch(Exception e){
            Log.e(TAG, "이미지 로딩 중 에러 발생: " + nextImageName, e);
        }

        // TableLayout에 표시된 모든 버튼을 지운다.
        // 테이블 레이아웃에 있는 행의 수 만큼 반복
        for(int row = 0; row < buttonTableLayout.getChildCount(); row++){
            // getCHildCount는 테 행의 개수를 의미
            // getChildAt(row) 행의 요소 전체를 갖어옴
            // 한행에 존재하는 모든 버튼을 지운다.
            ((TableRow)buttonTableLayout.getChildAt(row)).removeAllViews();
        }

        Collections.shuffle(fileNameList); // 파일명 목록을 섞는다.

        int correct = fileNameList.indexOf(answer);

        fileNameList.add(fileNameList.remove(correct)); // 정답 국기명을 추출해서 마지막에 놓는다.

        // guess_button.xml을 불러오기 위함
        //TableRow에 버튼을 배치할 도구를 생성
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        int file_count = 0;
        for(int row = 0; row < guessRows; row++){
            TableRow currentRow = getTableRow(row);

            // 현재 TableRow에 버튼을 배치한다.
            for(int column = 0; column < 3; column++){
                /*
                 * 테이블에 배치할 버튼 생성
                 */
                Button newGuessButton = (Button)inflater.inflate(R.layout.guess_button, null);

                String fileName = fileNameList.get(file_count);
                if (lang == LANG_KOREAN){
                    // 한글 국가명을 Map에서 읽어온다.
                    newGuessButton.setText(engkoMap.get(getNationName(fileName)));
                }else {
                    // 영어 국가명은 이미지 명에서 따왔으니 별도 처리 없음
//                    // 버튼의 텍스트
//                    String buttonText = getCountryName(fileNameList.get(file_count));
//                    newGuessButton.setText(buttonText);
                    newGuessButton.setText(getCountryName(fileName));
                }
                file_count++;
                //버튼 클릭시에 응답할 리스너 등록
                newGuessButton.setOnClickListener(guessButtonListener);

                /*
                 * 테이블에 버튼을 추가
                 */
                currentRow.addView(newGuessButton);
            }
        }

        // 정답을 가진 버튼을 무작위로 배치
        int row = (int)(Math.random() * guessRows);
        int column = (int)(Math.random() * 3);

        TableRow randomRow = getTableRow(row);
        String correctCountry = getCountryName(answer);

        if (lang == LANG_KOREAN){
            correctCountry = engkoMap.get(getNationName(answer));
        }else {
            correctCountry = getCountryName(answer);
        }

        ((Button) (randomRow.getChildAt(column))).setText(correctCountry);
    }

    private TableRow getTableRow(int row){
        return (TableRow) buttonTableLayout.getChildAt(row);
    }

    private View.OnClickListener guessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 선택된 버튼의 처리
            submitGuess((Button)v);
        }
    };

    // 국기 파일명에서 지역과 -기호를 제거해서 리턴
    private String getCountryName(String filename){
        return filename.substring(filename.indexOf('-')+1).replace("_"," ");
    }

    private String getNationName(String filename){
        return filename.substring(filename.indexOf('-')+1);
    }

    private void submitGuess(Button guessButton){
        String guess = guessButton.getText().toString();  // 사용자가 누른 버튼의 국가명을 알아냄
        String solutions = "";
        if (lang == LANG_KOREAN){
            solutions = engkoMap.get(getNationName(answer));
        }else{
            // 영문일 경우 정답
            solutions = getCountryName(answer);

        }

        totalGuesses++;     // 사용자가 시도한 횟수를 증가

        //선택한 답의 정답 여부를 검증
        if (guess.equals(solutions)){ // 정답인 경우
            correctCount++; // 정답의 갯수를 증가.

            answerTextView.setText(solutions + "!");
            answerTextView.setTextColor(getResources().getColor(R.color.correctColor));

            // 결과가 표시된 경우 버튼을 못 누르게 함
            disableButtons();

            if (correctCount == 10){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(R.string.reset_quiz);
                String message = String.format("총 10문제 중 %d 시도 %.2f 확률로 성공입니다!", totalGuesses, (10/totalGuesses * 100));
                builder.setMessage(message);

                builder.setCancelable(false);

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startQuiz();
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else { // 답은 맞았지만 퀴즈가 끝나지 않았을 때

                // 밀리세컨드 단위로 지연한 후에 모듈 실행
                handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                loadNextFlag();
                            }
                        }, 2000);
            }

        } else {    // 오답일 경우
            flagImageView.startAnimation(shakeAnimation);
            answerTextView.setText("땡! 오답입니다.");
            answerTextView.setTextColor(getResources().getColor(R.color.incoorectColor));
        }
    }

    private void disableButtons(){
        for(int row=0; row < buttonTableLayout.getChildCount(); ++row){
            TableRow tableRow = (TableRow)buttonTableLayout.getChildAt(row);

            for(int col = 0; col < tableRow.getChildCount(); col++){
                tableRow.getChildAt(col).setEnabled(false);
            }
        }
    }

    // 메뉴 처리
    // 메뉴 ID에 대한 상수 생성
    private final int CHOICES_MENU_ID = Menu.FIRST; // 선택할 답의 행 수
    private final int REGIONS_MENU_ID = Menu.FIRST + 1; // 지역 선택 메뉴
    private final int LANGUAGE_MENU_ID = Menu.FIRST + 2; // 버튼 표시 언어 선택 메뉴

    // 옵션 메뉴를 생성하는 메소드
    // 마우스 우클릭 > generate > override method 클릭해서 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 초기화
        super.onCreateOptionsMenu(menu);

        menu.add(0, CHOICES_MENU_ID, 0, R.string.choices);
        menu.add(0, REGIONS_MENU_ID, 0, R.string.regions);
        menu.add(0, LANGUAGE_MENU_ID, 0, R.string.languages);

        return true;
    }

    // 마우스 우클릭 > generate > override method 클릭해서 생성 > oOIS 치기
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case CHOICES_MENU_ID:
                final String[] choices = getResources().getStringArray(R.array.answerCount);
                AlertDialog.Builder choiceBuilder = new AlertDialog.Builder(this);
                choiceBuilder.setTitle(R.string.choices);
                choiceBuilder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        guessRows = Integer.parseInt(choices[which]) / 3;
                        startQuiz();
                    }
                });
                AlertDialog choiceDialog = choiceBuilder.create();
                choiceDialog.show();
                return true;

            case REGIONS_MENU_ID:
                final String[] regionNames = getResources().getStringArray(R.array.regionsList);

                boolean[] regionsEnabled = new boolean[regionsMap.size()];

                // 현재 체크되어 있는 지역들을 다이얼로그 목록에 초기화하기 위한 단계
                for(int i=0; i<regionsEnabled.length; i++){
                    regionsEnabled[i] = regionsMap.get(regionNames[i]);
                }

                AlertDialog.Builder regionsBuilder = new AlertDialog.Builder(this);
                regionsBuilder.setTitle(R.string.regions);

                regionsBuilder.setMultiChoiceItems(
                        regionNames,
                        regionsEnabled,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                // 체크하면 넣어주는!
                                regionsMap.put(regionNames[which], isChecked);
                            }
                        }
                );

                regionsBuilder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startQuiz(); // 지역 선택이 완료되었으므로 게임을 다시 시작
                            }
                        });

                AlertDialog regionsDialog = regionsBuilder.create();
                regionsDialog.show();

                return true;
            case LANGUAGE_MENU_ID:
                final String[] langSelect = getResources().getStringArray(R.array.langSelect);

                AlertDialog.Builder selectBuilder = new AlertDialog.Builder(this);
                selectBuilder.setTitle("표시할 언어 선택");

                selectBuilder.setSingleChoiceItems(
                        langSelect, lang, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (langSelect[which].equals(("한글"))){
                                    lang = LANG_KOREAN;
                                } else{
                                    lang = LANG_ENGLISH;
                                }
                            }
                        }
                );

                selectBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startQuiz();
                    }
                });

                AlertDialog selectDialog = selectBuilder.create();
                selectDialog.show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
