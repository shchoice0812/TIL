class MYIterator():
    def __init__(self, data):
        self.data = data
        self.position = 0

    def __iter__(self):
        return self
    
    def __next__(self):
        if self.position >= len(self.data):
            raise StopIteration
        result = self.data[self.position]
        self.position += 1
        return result

iterator = MYIterator([1, 2, 3, 4, 5])
for item in iterator:
    print(item)



num_list = [1, 2, 3, 4, 5]
iters =iter(num_list)
print(type(iters))
print(next(iters))
print(next(iters))
print(next(iters))
print(next(iters))
print(next(iters))
# print(next(iters))



t = 'ABCDEF'

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
print(next(wi))