class Vector():
    def __init__(self, x, y):
        # private 선언
        self.__x = float(x)
        self.__y = float(y)

    def __iter__(self):
        return (i for i in (self.__x, self.__y))
    
    @property
    def x(self):
        return self.__x

    @x.setter
    def x(self, v):
        self.__x = v

    @property
    def y(self):
        return self.__y

    @y.setter
    def y(self, v):
        self.__y = v


v1 = Vector(3, 5)

print(dir(v1))
print(v1.__dict__)

# getter 확인
print(v1.x, v1.y)

# setter 확인
v1.x = 10
v1.y = 20

print(f"x = {v1.x}, y = {v1.y}")

# Iterator Check
for v in v1:
    print(v)