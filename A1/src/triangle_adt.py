## @file triangle_adt.py
#  @author Mingzhe Wang
#  @brief Implementation of triangle ADT.
#  @date Jan 20, 2021

import math
from enum import Enum, auto

## @brief An Enum for representing different types of triangles.
#  @details Types are elements of the set {equilat, isosceles, scalene, 
#           right}.
class TriType(Enum):
	equilat = auto()
	isosceles = auto()
	scalene = auto()
	right = auto()

## @brief An ADT for representing triangles with integer sides.
#  @details Triangles are defined by its three sides.
class TriangleT:

	## @brief Constructor for TriangleT.
	#  @details Assumption 1: a triangle CAN be formed even if it is not valid 
	#    		(i.e.its three sides can not form a geometric triangle).<br/>
	#			Assumption 2: however, a triangle can be formed only if all its 
	#			three sides are positive integers.<br/>
	#			Assumption 3: to be consistent with Python's philospphy, no 
	#           type check.
	#  @param a Integer representing the first side of this triangle.
	#  @param b Integer representing the second side of this triangle.
	#  @param c Integer representing the third side of this triangle.
	#  @throws ValueError If some side(s) <= 0.
	def __init__(self, a, b ,c):
		if a > 0 and b > 0 and c > 0:
			self.__a = a
			self.__b = b
			self.__c = c
		else:
			raise ValueError("sides should > 0")

	## @brief Returns a tuple of three sides of the given triangle.
	#  @details The order of sides in the output tuple is as the order with 
	#           which they are constructed.
	#  @return Tuple representing the three sides of the triangle.
	def get_sides(self):
		return (self.__a, self.__b, self.__c)

	## @brief Checks if the current triangle is equal to another one.
	#  @details "Equal" here means these two triangles are geometric equal,
	#			i.e. their shapes are the same.
	#  @return True if the current triangle is equal to another one.
	#          False otherwise.
	def equal(self, newTri):
		if sorted(self.get_sides()) == sorted(newTri.get_sides()):
			return True
		else:
			return False

	## @brief Returns the perimeter of the current triangle.
	#  @details Assumption 1: the current triangle is a valid triangle.
	#  @return Integer representing the the perimeter of the current triangle.
	def perim(self):
		return self.__a + self.__b + self.__c

	## @brief Returns the area of the current triangle.
	#  @details Assumption 1: the current triangle is a valid triangle.
	#  @return Float representing the area of the current triangle.
	def area(self):
		# s is semi-perimeter of the given triangle
		# use 2.0 to set the float divide
		s = (self.__a + self.__b + self.__c) / 2.0
		# a is area of the given triangle
		a = math.sqrt(s * (s - self.__a) * (s - self.__b) * (s - self.__c))
		return a

	## @brief Checks if the current triangle is geometrically valid.
	#  @details A triangle is valid iff its three sides can form a triangle in 
	#           geometry.
	#  @return True if the current triangle can form a valid triangle.
	#		   False otherwise.
	def is_valid(self):
		return (self.__a + self.__b > self.__c
				and self.__a + self.__c > self.__b
				and self.__b + self.__c > self.__a)


	## @brief Returns the type of a valid triangle. The type is an element of 
	# 		  the set {equilat, isosceles, scalene, right}.
	#  @details Assumption 1: the current triangle must be a valid triangle.<br/>
	#			Assumption 2: if a triangle belongs to both equilat and isosceles, 
	#			it will be equilat. Because equilat is a more special type.<br/>
	#  			Assumption 3: if a triangle belongs to both scalene and right, 
	#			it will be right. Because right is a more specical type.
	#  @return TriType representing the shape of the triangle. It should be one 
	#		   of equilat, isosceles, scalene and right.
	def tri_type(self):
		if (self.__a ** 2 + self.__b ** 2 == self.__c ** 2
			or self.__a ** 2 + self.__c ** 2 == self.__b ** 2
			or self.__b ** 2 + self.__c ** 2 == self.__a ** 2):
			return TriType.right
		elif self.__a == self.__b == self.__c:
			return TriType.equilat
		elif (self.__a == self.__b
			or self.__b == self.__c
			or self.__a == self.__c):
			return TriType.isosceles
		else:
			return TriType.scalene

