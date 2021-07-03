## @file triangle_adt.py
#  @author Steven K.
#  @brief Python module for Triangles
#  @date 2020-01-20

import math
from enum import Enum

## @brief Enumeration class for all triangles types
class TriType(Enum):
    equilat = "Equilateral triangle"
    isoceles = "Isoceles triangle"
    scalene = "Scalene triangle"
    right = "Right-angle triangle"

## @brief Class for Triangles
class TriangleT:

    ## @brief Constructor for Complex Numbers
    #  @param a First side of the triangle
    #  @param b Second side of the triangle
    #  @param c Third side of the triangle
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

    ## @brief Getter method for all the sides of a triangle
    #  @return Tuple containing 3 sides of the triangle
    def get_sides(self):
        return (self.a,self.b,self.c)
    
    ## @brief Constructor for Complex Numbers
    #  @param t Second triangle
    #  @return True if both Triangles are equal and False if not
    def equal(self,t):
        setA = set(self.get_sides())
        setB = set(t.get_sides())
        if (setA == setB):
            return True
        else:
            return False
    
    ## @brief Calculates the perimeter of a triangle
    #  @return Perimeter of the triangle
    def perim(self):
        return (self.a+self.b+self.c)
    
    ## @brief Checks if the triangle is valid (mathematically possible)
    #  @return True if it's a valid triangle and False if not
    def is_valid(self):
        a = self.a
        b = self.b
        c = self.c
        if (a + b <= c) or (a + c <= b) or (b + c <= a): 
            return False
        else: 
            return True
    
    ## @brief Calculates the area of a triangle
    #  @throws Exception thrown if the triangle is not valid
    #  @return Area of the triangle
    def area(self):
        if self.is_valid():
            semiP = self.perim()/2
            return math.sqrt(semiP*(semiP-self.a)*(semiP-self.b)*(semiP-self.c))
        else:
            raise Exception("Please enter a valid triangle")
    
    ## @brief Returns the type of the triangle
    #  @throws Exception thrown if the triangle is not valid
    #  @return TriType object corresponding to the type of triangle
    def tri_type(self):
        a = self.a
        b = self.b
        c = self.c
        if self.is_valid():
            if (a**2 + b**2) == c**2:
                return TriType.right
            elif a == b == c:
                return TriType.equilat
            elif (a == b) or (b == c) or (a == c):
                return TriType.isoceles
            else:
                return TriType.scalene
        else:
            raise Exception("Please enter a valid triangle")
