"""
    First-class function 개념 이해
    * First-class function 이 되기 위한 조건
      1. 변수나 자료구조에 함수의 저장이 가능하다.
      2. 함수의 매개변수에 다른 함수의 인수로 전달 가능하다.
      3. 함수의 return값으로 함수를 전달할 수 있다.
    
    Python의 함수는 일급함수로 이를 증명해 보는 코드이다. 추가적으로 High-ordered function도 확인해 본다.
    상세 내용 참조 : https://velog.io/@shchoice/First-class-Function%EC%9D%BC%EA%B8%89-%ED%95%A8%EC%88%98-Higher-order-Function%EA%B3%A0%EC%9C%84-%ED%95%A8%EC%88%98
"""

# 1. 변수나 자료구조에 함수의 저장
def add(num1, num2):
    return num1 + num2

var = add

print(add) 
print(var) 
print(add is var)

print(add(2, 3))
print(var(2,3))
print(add(2, 3) == var(2,3))
print(add(2, 3) is var(2,3))


# 2. 함수의 매개변수에 함수를 인자로 사용
def square(x):
    return x * x

def first_class(func, arg_list):
    print(f"Running Function : {func.__name__}") ## Running Function : square

    return [func(i) for i in arg_list]

num_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
result = first_class(square, num_list)
print(result) ## [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]


# 3-1. 함수의 반환 값(return 값)으로 함수를 전달
def logger(message):
    
    def log_message():
        print(f"Log : {message}") ## Log : Hello Python!
    
    return log_message

log_hi = logger('Hello Python!')
# log message 객체 출력
print(log_hi) ## <function logger.<locals>.log_message at 0x00000186F5C5F040>
log_hi()

# 3-2. 함수의 반환 값(return 값)으로 함수를 전달
def add2(num1, num2):
    return num1 + num2

def first_class2(func):

    def addition(*args, **kwargs):
        print(f"Running Function : {func.__name__}") ## Running Function : add2
        print(f"Positional arguments : {args}") ## Positional arguments : (3, 5)
        print(f"Keyword argumets : {kwargs}") ## Keyword argumets : {}
        result = func(*args, **kwargs)

        return result

    return addition

decorated_add = first_class2(add2)
print(decorated_add) ## <function first_class2.<locals>.addition at 0x00000273FC3701F0>
print(decorated_add(3, 5)) ## 8


# 4. High-order function(고위 함수) 확인
def factorial(num):
    if num == 1:
        return 1
    else:
        return num * factorial(num-1)

var_func = factorial
# 일급 함수 확인
print(var_func)
print(var_func(10))

# 고위 함수 확인
print(map(var_func, range(1,11)))
print(list(map(var_func, range(1, 11))))
print(map(var_func, filter(lambda x: x % 2, range(1, 11))))
print(list(map(var_func, filter(lambda x: x % 2, range(1, 11)))))
print([var_func(i) for i in range(1, 11)])

from functools import reduce
from operator import add

print(reduce(add, range(1, 11)))
print(sum(range(1,11)))
print(reduce(lambda x, t : x + t, range(1, 11)))