# 객체 슬라이싱
class Objects:
    def __init__(self):
        self._numbers = [n for n in range(1, 1000, 3)]
    
    def __len__(self):
        return len(self._numbers)

    def __getitem__(self, idx):
        return self._numbers[idx]


s = Objects()
print(s.__dict__)
print(len(s))
print(len(s._numbers))
print(s[1:100])
print(s[-1])


# 파이썬 추상클래스
# 참고 : https://docs.python.org/3/library/collections.abc.html

# 추상클래스 사용 이유 : 개발과 관련된 공통된 내용(필드, 메소드) 추출 및 통합해서 공통된 내용으로 작성하게끔 함
# 자체적으로 객체 생성 불가하다.
# 상속을 통해서 자식 클래스에서 인스턴스를 생성해야한다.


# Sequence 상속 받지 않았지만 , 자동으로 __iter__, __contiain__ 기능 작동
# 파이썬 엔진이 객체 전체를 자동으로 조사 -> 시퀀스 프로토콜
class IterTestA():
    def __getitem__(self, idx):
        return range(1,50,2)[idx]

i1 = IterTestA()
print(i1[3])
print(i1[3:5])


# Sequence 상속
# 요구사항인 추상메소드를 모두 구현해야함
from collections.abc import Sequence
class IterTestB(Sequence):
    def __getitem__(self, idx):
        return range(1,50,2)[idx]

    def __len__(self, idx):
        return len(range(1,50,2)[idx])

i2 = IterTestB() #__len__ 이 없으면 TypeError: Can't instantiate abstract class IterTestB with abstract methods __len__ 발생 : 추상클래스 때문
print(i1[3])
print(i1[3:5])


# abc 활용 예제
import abc

class RandomMachine(abc.ABC):
    # 추상 메소드
    @abc.abstractmethod
    def load(self, iterobj):
        """Iterable 항목 추가"""
    
    # 추상 메소드
    @abc.abstractmethod
    def pick(self, iterobj):
        """무작위 항목 뽑기"""

    def inspect(self):
        items = []
        while True:
            try:
                items.append(self.pick())
            except LookupError:
                break
            return tuple(sorted(items))

import random

class CraneMachine(RandomMachine):
    def __init__(self, items):
        self._randomizer = random.SystemRandom()
        self._items = []
        self.load(items)

    def load(self, items):
        self._items.extend(items)
        self._randomizer.shuffle(self._items)

    def pick(self):
        try:
            return self._items.pop()
        except IndexError:
            raise LookupError('Empty Crane Box')
    
    def __call__(self):
        return self.pick()

# 서브 클래스 확인
print(issubclass(CraneMachine, RandomMachine))
print(issubclass(RandomMachine, CraneMachine))

# 상속 구조 확인
print(CraneMachine.__mro__)

cm = CraneMachine(range(1, 100))

print(cm._items)
print(cm.pick())
print(cm.inspect())