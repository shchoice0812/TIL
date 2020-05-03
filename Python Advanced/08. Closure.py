"""
    클로저는 하나의 함수로 여러가지 함수를 간단히 만들어낼 수 있으며, 기존에 만들어진 함수나 모듈을 수정하지 않고도 wrapper 함수를 이용해 커스터마이징할 수 있게 해준다.
"""

# 1-1. lotto program을 통한 이해
import random

def lotto_generate_num():
    num_list = [n for n in range(1, 10)]
    random.shuffle(num_list)

    def lotto_select_num():
        while(True):
            selected_list = sorted([random.choice(num_list) for i in range(6)])
            if len(set(selected_list)) == 6:
                break

        return selected_list

    return lotto_select_num

lotto_selected_nums = lotto_generate_num()
print(lotto_selected_nums())

# 2-1 list append를 통한 이해
def closure_avg1():
    # Free variable
    series = []
    def averager(v):
        series.append(v)
        print(series, len(series))
        return sum(series) / len(series)
    return averager

num_list = [1,2,3,4,5]
closure = closure_avg1()
print(closure(15))
print(closure(35))

# 2-2. list append 시 nonlocal 사용
def closure_avg2():
    cnt = 0
    total =0
    # 클로저 영역
    def averager(v):
        nonlocal cnt, total
        cnt += 1
        total += v

        return total / cnt

    return averager

closure = closure_avg2()
print(closure(15))
print(closure(35))