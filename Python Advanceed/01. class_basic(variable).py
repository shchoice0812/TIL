class Person():
    """
        Person class
        Author : shawn
        Date : 20.03.03
        Description : class variable, instance variable에 대한 기초를 이해하기 위한 예제
    """
    # 클래스 변수(클래스 변수 접근 시에는 클래스.클래스변수로 작성) : 어떠한 인스턴스(객체)든 사용 가능
    person_count = 0
    
    def __init__(self, name, age, birthday, height, etc):
        # 속성, 인스턴스 변수 : 인스턴스(객체)의 것
        self._name = name
        self._age = age
        self._birthday = birthday
        self._height = height
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
        print(f"person Info : {self._name}, {self._age}, {self._birthday}, {self._height}, {self._etc}")

    # Instance Method
    def __del__(self):
        Person.person_count -= 1


# 인스턴스 생성
person1 = Person("아이유", 28, "930516", 161, {'carreer1' : '호텔 델루나', 'carreer2' : '좋은날'})
person2 = Person("수지", 29, "941010", 166, {'carreer1' : '건축학개론', 'career2': '겨울아이'} )


# 객체에 대한 이해
## 개체 주소값 비교(메모리 내 다른 주소에 존재)
print(id(person1))
print(id(person2))

print(person1._name == person2._name)
print(person1 is person2)


# dir & __dict__ 이해
print(dir(person1)) # 파이썬 내장 함수까지 확인 가능
print(person1.__dict__) # 인스턴스 변수의 값(속성)을 보여줌


# DocString
print(Person.__doc__) # """ 내용 """(주석) 이 출력된다.


# 메소드 실행
person1.detail_info()
person2.detail_info()
# Person.detail_info(person1) 위와 같은 표현
# Person.detail_info(person2)


# 클래스 찾기
print(person1.__class__)
print(person2.__class__)
print(id(person1.__class__) == id(person2.__class__))


# 인스턴스 변수
# 직접 접근(PEP 문법적으로 권장하지 않음)
print(person1._name, person2._name)


# 클래스변수(누구나 접근 가능)
print(person1.person_count)
print(person2.person_count)
print(Person.person_count)
# 인스턴스 namespace에 없으면 상위 클래스에서 확인


# 객체 소멸
del person2
print(Person.person_count)