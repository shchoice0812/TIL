# yield : 메인루틴과 서브루틴이 통신할 수 있게끔 해줌
# 코루틴 제어, 코루틴 상태, 양방향 값 전송
# yield form

# 메인루틴이란? : 순차 진행(하나의 흐름) 서브 루틴인 함수 등을 쭈욱 진행,
# 서브루틴을 동시에 할 수있게하는 것이 yield이다. 하나를 수행학고 기억하고 다른거 수행하고 기억하고
# 서브루틴 : 메이루틴에서 리턴에 의해 호출 부분으로 돌아와 다시 프로세스 시작
# 코루틴 : 루틴 실행 중 멈춤 가능 -> 특정 위치로 돌아갔다가 다시 원래 위치로 돌아와 수행 가능(동시성 처리 가능), 디버깅에 어려움(여기저기 왔다갔다 해서 디버깅 어려움), 멀티 프로세스 기능과 멀티 스레딩은 불가능한듯
# 코루틴 : 코루틴 스케줄링 오버헤드 매우 적다.(싱글 스레드이기 때문이다.) 멀티 쓰레드는 컨텍스트 스위칭(스케줄링)으로 오버헤드 발생
# 쓰레드 : 싱글 쓰레드 만 지금까지 사용 했지만 -> 멀티 쓰레드 사용 (복잡, 공유되는 자원에 멀티쓰레드는 교착상태 발생 가능하기 때문(lo차 ,context switching 비용이 들음, 자원 소비 가능성 중간))


# 코루틴 예제1
def coroutine1():
    print("coroutine started")
    i = yield # 저번시간엔 yiled 'aaa'로 return만 했지만, 지금은 메인 루틴에게 값을 받을 수 있다.(따라서 양방향 통신)
    print(f"coroutine received : {i}")

# 제너레이터 선언
c1 = coroutine1()
print(c1, type(c1))

# yield 실행 전까지 진행
#next(c1)
# 기본으로 None 전달
# c1 값전달
#c1.send(100)

# 잘못된 사용
# c1 = coroutine1()
# c1.send(100)



# 코루틴 예제 2
# GEN_CREATED : 처음 대기 상태
# GEN_RUNNING : 실행 상태
# GEN_SUSPENDED : yield 대기 상태
# GEN_CLOSED : 실행 완료 상태

def coroutine2(x):
    print(f"coroutine started : {x}")
    y = yield x  # 메인루틴에 받은것(y)과 보낼것(x)
    print(f"corutine received : {y}")
    z = yield x + y
    print(f"corutine received : {z}")

c3 = coroutine2(10)

from inspect import getgeneratorstate
print(getgeneratorstate(c3))
print(next(c3))
print(getgeneratorstate(c3))
print(c3.send(15))
print(getgeneratorstate(c3))
# print(c3.send(15))


#  데코레이터 패턴
from functools import wraps

def coroutine(func):
    '''Decorator run until yield'''
    @wraps(func)
    def primer(*args, **kwargs):
        gen = func(*args, **kwargs)
        next(gen)
        return gen
    return primer

@coroutine
def sumar():
    total = 0
    term =0
    while True:
        term = yield total
        total += term

su = sumar()
print(su.send(100))
print(su.send(40))
print(su.send(60))


# 코루틴 예제 3(예외처리)

class SampleException(Exception):
    '''설명에 사용할 예외 유형'''
def coroutine_except():
    print("coroutine started")
    try:
        while True:
            try:
                x = yield
            except SampleException:
                print("SampleException handled. Continuing")
            else:
                print(f"coroutine received : {x}")
    finally:
        print("coroutine ending")

exe_co = coroutine_except()
print(next(exe_co))
print(exe_co.send(10))
print(exe_co.send(100))
print(exe_co.throw(SampleException))
print(exe_co.send(100))
print(exe_co.close())
#print(exe_co.send(100))


# 코루틴 예제4(return)
def averager_re():
    total = 0.0
    cnt =0
    avg = None
    while True:
        term = yield
        if term is None:
            break
        total += term
        cnt += 1
        avg = total / cnt
    return f'Average : {avg}'

avger2 = averager_re()
next(avger2)

avger2.send(10)
avger2.send(30)
avger2.send(50)

try:
    avger2.send(None)
except StopIteration as e:
    print(e.value)


# 코루틴 예제5 (yield from)
# StopIteration 자동 처리(await으로 바뀜)
# 중첩 코루틴 처리

def gen1():
    # 총 5번 수행해야 끝남
    for x in 'AB':
        yield x
    for y in range(1,4):
        yield y

t1 = gen1()
print(next(t1))
print(next(t1))
print(next(t1))
print(next(t1))
print(next(t1))


t2 = gen1()
print( list(t2)) # generate로 다 반환(중첩 반환)

def gen2():
    yield from 'AB'
    yield from range(1,4)
    

t3 = gen2()
print(next(t3))
print(next(t3))
print(next(t3))
print(next(t3))
print(next(t3))


t4 = gen2()
print(list(t4))



def gen3_sub():
    print("sub coroutine")
    x = yield 10
    print("recv", str(x))
    x = yield 100
    print("recv", str(x))

def gen4_main():
    yield from gen3_sub()

t5 =gen4_main()

print(next(t5))
print(t5.send(7))
print(t5.send(77))