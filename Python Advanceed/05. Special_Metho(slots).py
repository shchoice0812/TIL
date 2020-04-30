"""
    slots의 개념
      - 파이선 인터프리터에게 통보
      - 해당 클래스가 가지는 속성을 제한
      - __dict__는 hash table 사용으로 메모리 사용이 많음,
        따라서 __slots를 사용하며 다수의 객체 생성 에는  메모리 사용(공간) 대폭 감소 효과
      - 해당 클래스에 만들어진 인스턴스 속성 관리에 dictionary 대신 Set 형태를 사용
      - 대용량 데이터 처리를 위해 ML, DL 모델에는 대부분 slots를 사용
"""

# __slot__, __dict__ 성능 비교 (클래스 2개 선언)
class TestA():
    __slots__ = ('a',) # Set 자료구조, 불변형

class TestB():
    pass    # slot 미사용으로 모든 attribute를 dict로 관리

use_slot = TestA()
no_slot = TestB()

print(use_slot)
#print(use_slot.__dict__) #slot 사용으로 미생성(AttributeError 발생)
print(no_slot)
print(no_slot.__dict__)


## 메모리 사용량 비교
import timeit

# 측정을 위한 함수 선언
def repeat_outer(obj):
    def repeat_inner():
        obj.a ='TEST'
        del obj.a
    return repeat_inner

print(timeit.repeat(repeat_outer(use_slot), number=100000000))
print(timeit.repeat(repeat_outer(no_slot), number=100000000))

