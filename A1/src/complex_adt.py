## @file complex_adt.py
#  @author Mingzhe Wang
#  @brief Implementation of complex number ADT.
#  @date Jan 20, 2021

import math

## @brief An ADT for reprensenting a complex number.
class ComplexT:

	## @brief Constructor for ComplexT.
	#  @details Create a complex number by provding two floats number as the 
	#			real and the imaginary number, respectively.<br/>
	#           Assumption 1: the input must be float, there is no auto float 
	#           converting.<br/>
	#           Assumption 2: as it is Python's philosophy, no type check 
	#           performed.
	#  @param x Float representing the real part of the complex number.
	#  @param y Float representing the imaginary part of the complex number.
	def __init__(self, x, y):
		self.__x = x
		self.__y = y

	## @brief Returns the real part of the complex number.
	#  @return Float representing the real part of the complex number.
	def real(self):
		return self.__x

	## @brief Returns the imaginary part of the complex number.
	#  @return Float representing the imaginary part of the complex number.
	def imag(self):
		return self.__y

	## @brief Returns the absolute value of the complex number.
	#  @return Float representing the absolute value of the complex number.
	def get_r(self):
		return math.sqrt(self.__x ** 2 + self.__y ** 2)

	## @brief Returns the phase of the complex number.
	#  @details The value of the phase is expressed in radians.<br/>
	#   		Assumption 1: the real part and the imaginary part of the 
	#           given complex number should not be both zero.
	#  @return Float representing the phase in radians of the complex number.
	#          This value is in the internal (-pi, pi].
	#  @throws ZeroDivisionError if the real and imaginary parts of the 
	#		   current complex number are so small that raises float 
	#          division by zero error.
	def get_phi(self):
		if self.__x < 0 and self.__y == 0:
			return math.pi
		else:
			return 2 * math.atan(self.__y 
				/ (math.sqrt(self.__x ** 2 + self.__y ** 2) + self.__x))

	## @biref Checks if the current complex numer is equal to another complex 
	#		  number.
	#  @details The two complex number is equal iff both the real parts and 
	#			the imaginary parts of them equal.<br/>
	#           Assumption 1: this equal is an "exact" equal, bit-by-bit 
	#			equal.
	#  @param newCom ComplexT Another complex number to be compared with.
	#  @return True if the current complex numer is equal to another complex 
	#		   number.
	#          False otherwise.
	def equal(self, newCom):
		return self.__x == newCom.__x and self.__y == newCom.__y

	## @brief Returns a complex number that is the complex conjugate of the 
	#         current complex number.
	#  @return ComplexT representing the complex conjugate of the current 
	#          complex number.
	def conj(self):
		c = ComplexT(self.__x, -self.__y)
		return c

	## @brief Calculates a complex number representing the result of adding
	#         another complex number to the current one.
	#  @details Assumption 1: the behaviour of this method is a getter and 
	#			will not imutate the current object.
	#  @param newCom ComplexT Another complex number to be added to the 
	#			current one.
	#  @return ComplexT representing the result of the addition.
	def add(self, newCom):
		x = self.__x + newCom.__x
		y = self.__y + newCom.__y
		c = ComplexT(x, y)
		return c

	## @brief Calculates a complex number representing the result of 
	#	      subtracting another complex number from the current one.
	#  @details Assumption 1: the behaviour of this method is a getter and 
	#			will not imutate the current object.
	#  @param newCom ComplexT representing the subtrahend of the subtraction.
	#  @return ComplexT representing the result of the subraction, which is  
	#          the current complex number - another complex number.
	def sub(self, newCom):
		x = self.__x - newCom.__x
		y = self.__y - newCom.__y
		c = ComplexT(x, y)
		return c

	## @brief Calculates a complex number representing the result of 
	#         multiplying the current complex number with another complex 
	#		  number.
	#  @details Assumption 1: the behaviour of this method is a getter and 
	#			will not imutate the current object.
	#  @param newCom ComplexT representing the multiplier of the 
	#		  multiplication.
	#  @return ComplexT representing the result of the multiplication, which 
	#          is the current complex number * another complex number.
	def mult(self, newCom):
		x = self.__x * newCom.__x - self.__y * newCom.__y
		y = self.__x * newCom.__y + self.__y * newCom.__x
		c = ComplexT(x, y)
		return c

	## @brief Calculates the reciprocal of the current complex number.
	#  @details Assumption 1: the behaviour of this method is a getter and 
	#			will not imutate the current object.<br/>
	#           Assumption 2: the current complex number must be non-zero.
	#  @return ComplexT representing the reciprocal of the current complex 
	#          number.
	#  @throws ZeroDivisionError if the real and imaginary parts of the 
	#		   current complex number are so small that raises float 
	#          division by zero error.
	def recip(self):
		x = self.__x / (self.__x ** 2 + self.__y ** 2)
		y = - (self.__y / (self.__x ** 2 + self.__y ** 2))
		c = ComplexT(x, y)
		return c


	## @brief Calculates a complex number representing the result of dividing 
	#         the current complex number by another complex number.
	#  @details Assumption 1: the behaviour of this method is a getter and 
	#			will not imutate the current object.<br/>
	#           Assumption 2: the argument must be an non-zero complex number.
	#  @param newCom ComplexT representing the divisor of the divison.
	#  @return ComplexT representing the result of the division, which is 
	#  		   current Complex number divides by another complex number.
	#  @throws ZeroDivisionError if the real and imaginary parts of the 
	#		   argument complex number are so small that raises float 
	#          division by zero error.
	def div(self, newCom):
		x = ((self.__x * newCom.__x + self.__y * newCom.__y) 
			 / (newCom.__x ** 2 + newCom.__y ** 2))
		y = ((self.__y * newCom.__x - self.__x * newCom.__y)
			 / (newCom.__x ** 2 + newCom.__y ** 2))
		c = ComplexT(x, y)
		return c

	## @brief Calculates the positive square root of the current object.
	#  @details Assumption 1: the imaginary part of the current complex number
	#           is not equal to 0.<br/>
	#			Assumption 2: the result is the positive square root.<br/>
	#           Assumption 3: the behaviour of this method is a getter and 
	#			will not imutate the current object.
	#  @return ComplexT represnting the positive square root of the current
	#          complex number.
	def sqrt(self):
		absValue = self.get_r()
		x = math.sqrt((self.__x + absValue) / 2)
		if self.__y > 0:
			y = 1 * math.sqrt(((- self.__x) + absValue) / 2)
		else:
			y = (- 1) * math.sqrt(((- self.__x) + absValue) / 2)
		c = ComplexT(x, y)
		return c



