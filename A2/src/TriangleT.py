## @file TriangleT.py
#  @author Mingzhe Wang (wangm235)
#  @brief An equilateral triangle ADT implementing the shape inerface.
#  @date 2021 Feb 16

from Shape import Shape


## @brief A class representing an equilateral triangle.
#  @details This equilateral triangle should implement all the methods specified in Shape int
#           erface.<br/> Assumptions: The arguments provided to the access programs will be of
#            the correct type.
class TriangleT(Shape):

    ## @brief Constructor for TriangleT.
    #  @details Constructor taking in x-coordinate, y-coordinate, side length, and mass for an
    #  equilateral triangle object.
    #  @param x A real number representing the x-coordinate of the triangle centre.
    #  @param y A real number representing the y-coordinate of the triangle centre.
    #  @param s A real number representing the side length of the triangle.
    #  @param m A real number representing the mass of the triangle object.
    #  @throws ValueError If side length or mass is not positive.
    def __init__(self, x, y, s, m):
        if not (s > 0 and m > 0):
            raise ValueError
        ## X-coordinate of the triangle centre.
        self.x = x
        ## Y-coordinate of the triangle centre.
        self.y = y
        ## Side length of the triangle.
        self.s = s
        ## Mass of the triangle object.
        self.m = m

    ## @brief Returns the x-coordinate of the centre of mass.
    #  @details The centre of mass is calculated under the physic law.
    #  @return A real number representing the x-coordinate of the centre of mass.
    def cm_x(self):
        return self.x

    ## @brief Returns the y-coordinate of the centre of mass.
    #  @details The centre of mass is calculated under the physic law.
    #  @return A real number representing the y-coordinate of the centre of mass.
    def cm_y(self):
        return self.y

    ## @brief Returns the mass of the triangle object.
    #  @return A real number representing the mass of the triangle object.
    def mass(self):
        return self.m

    ## @brief Returns the the moment of inertia of the triangle object.
    #  @details For a triangle object, the moment of inertia is equal to (m * s**2)/ 12.
    #  @return A real number representing the moment of inertia of the triangle object.
    def m_inert(self):
        return self.m * self.s**2 / 12.0
