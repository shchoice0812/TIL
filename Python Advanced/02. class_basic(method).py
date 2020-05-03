class Person():
    """
        Person class
        Author : shawn
        Date : 20.03.03
        Description : class method, instance method, static method에 대한 기초를 이해하기 위한 예제
    """
    # class variable
    person_count = 0
    annual_interest_rate = 1.1
    
    def __init__(self, name, age, birthday, height, deposit, etc):
        # 속성, 인스턴스 변수 : 인스턴스(객체)의 것
        self._name = name
        self._age = age
        self._birthday = birthday
        self._height = height
        self._deposit = deposit
        self._etc = etc

        Person.person_count += 1
    
    # Instance Method
    def __str__(self):
        return f'str : {self._name}'

    # Instance Method
    def __repr__(self):
        return f'repr : {self._name}'

    # Instance Method
    def detail_info(self):
        print(f"Current Id : {id(self)}", end = ',\t')
        print(f"person Info : {self._name}, {self._age}, {self._birthday}, {self._height}, {self._deposit} {self._etc}")

    #Instance Method
    def current_deposit(self):
        print(f"금년 예금 : {self._name}, {self._deposit}")

    #Instance Method
    def next_year_deposit(self):
        print(f"내년 이자율 적용 후 예금 : {self._name}, {self._deposit * Person.annual_interest_rate}")

    # Instance Method
    def __del__(self):
        Person.person_count -= 1

    # 모든 인스턴스가 공통으로 사용하는 메소드일 경우 사용(class 자체가 넘어옴) 
    @classmethod
    def change_annual_interest_rate(cls, interest_rate):
        cls.annual_interest_rate = interest_rate

    # 나중에 추가하는 인스턴스에 대해서는 class method를 사용
    @classmethod
    def person_constructor(cls, name, age, birthday, height, deposit, etc):
        return cls(name, age, birthday, height, deposit, etc)

    # self, cls 상관없이 그 누구나 사용 가능
    @staticmethod
    def billionare_check(celebrity):
        if celebrity._deposit > 10000 :
            print("억만장자입니다.")
        else:
            print("조금만 더 힘내세요!")


# 인스턴스 생성
person1 = Person("아이유", 28, "930516", 161, 10000, {'carreer1' : '호텔 델루나', 'carreer2' : '좋은날'})
person2 = Person("수지", 29, "941010", 166, 30000, {'carreer1' : '건축학개론', 'career2': '겨울아이'} )

# 전체정보 출력
person1.detail_info()
person2.detail_info()
print()

# 연이율 변경 이전 예금 정보
print("* 연이율 변경 전 예금 정보")
person1.current_deposit()
person1.next_year_deposit()
print()

# 연이율 증가
Person.change_annual_interest_rate(2)

# 연이율 변경 이후 예금 정보
print("* 연이율 변경 후 예금 정보")
person1.current_deposit()
person1.next_year_deposit()


# person3 인스턴스(객체) 생성, 새로 생성하는 것이 명확
# PEP 에서는 나중에 추가하는 인스턴스에 대해서는 class method를 통해 해결할 것을 권장
person3 = Person.person_constructor("아이린", 39, "910329", 158, 50000, {'career1': '러시안 룰렛', 'career2': '빨간맛'})
person3.detail_info()
print(person3._deposit)
print()

# static method 확인
print("static method 확인")
person1.billionare_check(person1)
person2.billionare_check(person2)
person3.billionare_check(person3)
## 위와 같은 표현
Person.billionare_check(person1)
Person.billionare_check(person2)
Person.billionare_check(person3)