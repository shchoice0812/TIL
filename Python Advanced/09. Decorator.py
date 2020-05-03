"""
    Decorator의 동작 원리를 이해하기 위한 코드입니다.
    Decorator를 이해하기 위해 time 모듈을 활용했습니다.
    추가적으로 time 모듈에 대해 공부했던 내용은
    1. time.process_time() 과 time.perf_counter()의 차이입니다.
      * perf_counter 함수는 sleep 함수의 호출 시간도 포함하지만,
      * process.time 함수는 sleep 함수는 제외하고 실제 연산 시간만 계산합니다.
"""

import time

# 1. decorate example using by time.process_time()
def decorate(original_func):

    def wrapper(*args, **kwargs):
        start = time.process_time()
        original_func(*args, **kwargs)
        end = time.process_time()
        
        name = original_func.__name__
        arg_str = ', '.join(repr(arg) for arg in args)
        print(f"1. 함수 이름 : {name}({arg_str})")
        print("2. 수행시간 : %0.20f초" %(end-start))

    return wrapper

# 2. decorate example using by time.perf_counter()
def perf_clock(original_func):

    def time_count(*args, **kwargs):
        start = time.perf_counter()
        original_func(*args, **kwargs)
        end = time.perf_counter()
        
        name = original_func.__name__
        arg_str = ', '.join(repr(arg) for arg in args)
        print(f"1. 함수 이름 : {name}({arg_str})")
        print("2. 수행시간 : %0.05f초" %(end-start))
    
    return time_count



@decorate
def func1(seconds):
    print("func1 start")
    time.sleep(seconds)
    for i in range(1, 100000000):
        pass
    
func1(2)
print()

@perf_clock
def func2(seconds):
    print("func2 start")
    time.sleep(seconds)
    for i in range(1, 100000000):
       pass

func2(2)
print()

# if not use decorate, use like below
# nodecorate = decorate(myfunc1)
# nodecorate()