## @file complex_adt.py
#  @author Steven K.
#  @brief Python module for Complex Numbers
#  @date 2021-01-16

import math


## @brief Class for Complex Numbers
class ComplexT:

	## @brief Constructor for Complex Numbers
	#  @param x The real part of the complex number
	#  @param y The imaginary part of the complex number
    def __init__(self,x,y):
        self.x = x
        self.y = y

    ## @brief Returns the real part of a complex number
    #  @return Real part of the complex number
    def real(self):
        return self.x

    ## @brief Returns the imaginary part of a complex number
    #  @return Imaginary part of the complex number
    def imag(self):
        return self.y

    ## @brief Calculates the absolute value of a complex number
    #  @return Absolute value of a complex number
    def get_r(self):
        return math.sqrt(self.x**2 + self.y**2)

    ## @brief Calculates the phase of a complex number
    #  @throws Exception thrown if the phase of the complex number is not defined 
    #  @return Phase of a complex number
    def get_phi(self):
        if (self.x < 0) and (self.y == 0):
            return math.pi
        elif (self.real() == 0) and (self.imag() == 0):
            raise Exception("Phase of this number is not defined")
        else:
            return 2 * math.atan(self.y / (self.get_r()+self.x))

    ## @brief Compares two complex numbers and checks if they are equal
    #  @param c Second complex number
    #  @return True if both complex numbers are equal, or False if they are not
    def equal(self,c):
        if (self.real() == c.real()) and (self.imag() == c.imag()):
            return True
        else:
            return False


    ## @brief Calculates the conjugate of a complex number
    #  @return Conjugate of the complex number
    def conj(self):
        return ComplexT(self.x, self.y*(-1))

    ## @brief Adds two complex numbers
    #  @param c Second complex number
    #  @return Result of the complex number addition
    def add(self,c):
        return ComplexT((self.x+c.real()),(self.y+c.imag()))

    ## @brief Subtracts two complex numbers
    #  @param c Second complex number
    #  @return Result of the complex number subtraction
    def sub(self,c):
        return ComplexT((self.x-c.real()),(self.y-c.imag()))

    ## @brief Multiplies two complex numbers
    #  @param c Second complex number
    #  @return Result of the complex number multiplication
    def mult(self,c):
        return ComplexT((self.x*c.real()-self.y*c.imag()),(self.x*c.imag()+self.y*c.real()))

    ## @brief Calculates the reciprocal of a complex number
    #  @throws Exception thrown if the complex number is zero
    #  @return Reciprocal of the complex number
    def recip(self):
        if (self.x == 0) and (self.y == 0):
            raise Exception("Division by zero is not possible")
        else:
            return ComplexT((self.x/(self.x**2+self.y**2)),(self.y/(self.x**2+self.y**2))*(-1))

    ## @brief Divides a complex number by another complex number
    #  @param c Denominator
    #  @throws Exception thrown if the denominator is zero
    #  @return Result of the complex number division 
    def div(self,c):
        if (c.real() == 0) and (c.imag() == 0):
            raise Exception("Division by zero is not possible")
        else:
            return self.mult(c.recip())

    ## @brief Calculates the square root of a complex number
    #  @return Square root of the complex number
    def sqrt(self):
        if (self.y < 0):
            return ComplexT(math.sqrt(((self.x+self.get_r())/2)),(-1)*math.sqrt(((self.x*(-1)+self.get_r())/2)))
        else:
            return ComplexT(math.sqrt(((self.x+self.get_r())/2)),math.sqrt(((self.x*(-1)+self.get_r())/2)))
