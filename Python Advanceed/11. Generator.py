# Generator

# 파이썬 반복형 종류 : for, collections, text file, List, Dict, Set, Tuple, unpacking, *args
# 공부할 것 : 반복형 객체 내부적으로 iter 함수 내용, 제너레이터 동작 원리, yield from

# 반복 가능한 이유? iter(x) 함수 호출

# sequece 형
t = 'ABCDEF'

for c in t:
    print(c)


# while 사용
w = iter(t)

while True:
    try :
        print(next(w))
    except StopIteration as log:
        print(log)
        break

from collections import abc
# 반복형 확인
print(hasattr(t, '__iter__'))
print(isinstance(t, abc.Iterable))
print(hasattr(w, '__iter__'))
print(isinstance(w, abc.Iterable))

# next 사용
class WordSplitIter:
    def __init__(self, text):
        self._idx = 0
        self._text = text.split(' ')
    
    def __next__(self):
        try:
            word = self._text[self._idx]
        except IndexError:
            raise StopIteration()
        self._idx +=1
        return word

    def __iter__(self):
        print("Called __iter__")
        return self

    def __repr__(self):
        return 'WordSplit(%s)' % (self._text)

wi = WordSplitIter("I am studying python and it is fun")

print(wi)
print(next(wi))
print(next(wi))
print(next(wi))
print(next(wi))
print(next(wi))
print(next(wi))
print(next(wi))
print(next(wi))
#print(next(wi))


# generator 패턴 왜 사용하는가 : 
# 1. 지능형 리스트, 딕셔너리, 집합 -> 데이터 셋이 증가될 경우 메모리 사용량 증가 -> 제너레이터로 완화
# 2. 단위 실행 가능한 코루틴(coroutine) 구현에 아주 중요
# 3. 딕셔너리 리스트 한번 호출할 때마다 하나의 값만 리턴 -> 아주 작은 메모리 양을 필요로 함 Good

# yield가 무엇인지는 위의 iter class와 비교하면됨
class WordSplitGenerator:
    def __init__(self, text):
        self._text = text.split(' ')

    def __iter__(self):
        for word in self._text:
            yield word # 제너레이터
        return

    def __repr__(self):
        return 'WordSplit(%s)' % (self._text)

wg = WordSplitGenerator("I am studying python and it is fun")

wt = iter(wg)

print(wi)
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
# print(next(wt))


# Generator 예제1
def generator_ex1():
    print("start")
    yield 'AAA' # 여기서 멈춤 (closure개념도?)
    print("continue")
    yield 'BBB'
    print("end")

temp = iter(generator_ex1())

# print(next(temp))
# print(next(temp))
# print(next(temp))


# for v in generator_ex1():
#     print(vars)

# Generator 예제2

temp2 = [x * 3 for x in generator_ex1()]
temp3 = (x * 3 for x in generator_ex1()) # 지능형 제너레이터 (빅데이터 다룰때!! 파이썬스파크,, 하둡, 판다스 등)

print(temp2)
print(temp3)

for i in temp2:
    print(i)

for i in temp3:
    print(i)


# Generator 예제3 (자주 사용하는 함수)
import itertools

# 무한히 생성되는 itertools.count
gen1 = itertools.count(1, 2.5)
print(next(gen1))
print(next(gen1))
print(next(gen1))
print(next(gen1))


# 조건 
gen2 = itertools.takewhile(lambda n : n < 1000, itertools.count(1, 2.5))

for v in gen2:
    print(v)



# 필터 반대
gen3 = itertools.filterfalse(lambda n : n < 3, [1,2,3,4,5])

for v in gen3:
    print(v)


# 누적 합계
gen4 = itertools.accumulate([x for x in range(1, 101)])

for v in gen4:
    print(v)

# 연결1
gen5 = itertools.chain('ABCDE', range(1,11,2))
print(list(gen5))

gen6 = itertools.chain(enumerate('ABCDE')) # 벡터에 많이 사용
print(list(gen6))

# 개별
gen7 = itertools.product('ABCDE')
print(list(gen7))


# 연산
gen8 = itertools.product('ABCDE', repeat = 2)
print(list(gen8))

# 그룹화
gen9 = itertools.groupby('AAABEEBBCCCCDDEEE')
# print(list(gen9))
for chr, group in gen9:
    print(chr, " : ", list(group))