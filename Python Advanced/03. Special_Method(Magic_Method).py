# Special Method(Magic Method)
# cf1) https://docs.python.org/3/reference/datamodel.html#special-method-names
# cf2) https://www.tutorialsteacher.com/python/magic-methods-in-python

### 1. 파이썬 Special Mehod란 무엇인가.
print(dir(int))
"""
print(dir(int)) 결과 아래의 것들이 나오게 되는데 __가 앞, 뒤로 붙은 것을 Special Method, Magic Method라고 한다.

['__abs__', '__add__', '__and__', '__bool__', '__ceil__', '__class__', '__delattr__', '__dir__', '__divmod__', '__doc__', '__eq__', 
'__float__', '__floor__', '__floordiv__', '__format__', '__ge__', 
'__getattribute__', '__getnewargs__', '__gt__', '__hash__', '__index__', '__init__', '__init_subclass__', '__int__', '__invert__', '__le__', '__lshift__', '__lt__', '__mod__', '__mul__', '__ne__', '__neg__', '__new__', '__or__', '__pos__', '__pow__', '__radd__', '__rand__', '__rdivmod__', '__reduce__', '__reduce_ex__', '__repr__', '__rfloordiv__', '__rlshift__', '__rmod__', '__rmul__', '__ror__', '__round__', '__rpow__', '__rrshift__', '__rshift__', '__rsub__', '__rtruediv__', '__rxor__', '__setattr__', '__sizeof__', '__str__', '__sub__', '__subclasshook__', '__truediv__', '__trunc__', '__xor__', 'as_integer_ratio', 'bit_length', 'conjugate', 'denominator', 'from_bytes', 'imag', 'numerator', 'real', 'to_bytes']

"""

num = 100
print("Special Method Ex :", num.__add__(200)) # n + 200 과 같다.
print("Special Method Ex :", num.__mul__(2)) # n * 200 과 같다.
print("Special Method Ex :", num.__bool__()) # bool(num) 과 같다.


### 2. Vector class 생성을 통한 Special Method 이해
class Vector():
    def __init__(self, *args):
        """
        Create a Vecotr
        example : v = Vector(10, 20)
        """
        if len(args) == 0:
            self._x = 0
            self._y = 0
        else:
            self._x, self._y = args

    def __repr__(self):
        """ Returns information of two-dimensional vector """
        return "Vector(%s, %s)" %(self._x, self._y)
    
    def __add__(self, operand):
        """ returns addition of vector """
        return Vector(self._x + operand._x, self._y + operand._y)

    def __sub__(self, operand):
        """ returns subtraction of vector """
        return Vector(self._x - operand._x, self._y - operand._y)

    def __mul__(self, operand):
        """ returns multiplication of vector """
        return Vector(self._x * operand._x, self._y * operand._y)

    def __bool__(self):
        """ returns boolean of maximum number of two """
        return bool(max(self._x, self._y))


# Vector instance initialize
v1 = Vector(2,4)
v2 = Vector(3,6)

print("v1 : ", v1)
print("v2 : ", v2)
print("벡터의 합 v1 + v2 :", v1 + v2)
print("벡터의 합 v1 - v2 :", v1 - v2)
print("벡터의 합 v1 * v2 :", v1 * v2)
print("벡터 v1의 boolean:", bool(v1))
# cf) 불가능 : print("벡터의 합 v1 + 2 :", v1 + 3)     def __add__(self, y) 선언 시 가능

v3 = Vector()
print(v3)


### 3. Person Class age operation Example
class Person():    
    def __init__(self, name, age):
        self._name = name
        self._age = age
    
    # Instance Method
    def __str__(self):
        return f'str : {self._name}'

    def __ge__(self, x):
        """ returns a is greater than or equal to """
        if self._age >= x._age:
            return True
        else:
            return False

    def __le__(self, x):
        """ returns a is less than or equal to """
        if self._age <= x._age:
            return True
        else:
            return False
    
    def __add__(self, x):
        """ returns addition of two ages """
        return self._age + x._age

    def __sub__(self, x):
        """ returns subtraction of two ages """
        return self._age - x._age

    def __mul__(self, x):
        """ returns multiplication of two ages """
        return self._age * x._age


person1 = Person("아이유", 28)
person2 = Person("아이린", 30)

print("__ge__ :", person1 >= person2)
print("__le__ :", person1 <= person2)
print("__add__ :", person1 + person2)
print("__sub__ :", person1 - person2)
print("__mul__ :", person1 * person2)
