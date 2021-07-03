## @file CircleT.py
#  @author Mingzhe Wang (wangm235)
#  @brief A circle ADT implementing the shape inerface.
#  @date 2021 Feb 16

from Shape import Shape


## @brief A class representing a circle.
#  @details This circle should implement all the methods specified in Shape interface.<br/>
#           Assumptions: The arguments provided to the access programs will be of the correct
#           type.
class CircleT(Shape):

    ## @brief Constructor for CircleT.
    #  @details Constructor taking in x-coordinate, y-coordinate, radius, and mass for a
    #           circle object.
    #  @param x A real number representing the x-coordinate of the circle center.
    #  @param y A real number representing the y-coordinate of the circle center.
    #  @param r A real number representing the radius of the circle object.
    #  @param m A real number representing the mass of the circle object.
    #  @throws ValueError If radius or mass is not positive.
    def __init__(self, x, y, r, m):
        if not (r > 0.0 and m > 0.0):
            raise ValueError
        ## X-coordinate of the circle center.
        self.x = x
        ## Y-coordinate of the circle center.
        self.y = y
        ## Radius of the circle object.
        self.r = r
        ## Mass of the circle object.
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

    ## @brief Returns the mass of the circle object.
    #  @return A real number representing the mass of the circle object.
    def mass(self):
        return self.m

    ## @brief Returns the the moment of inertia of the circle object.
    #  @details For a circle object, the moment of inertia is equal to (m * r**2) / 2.
    #  @return A real number representing the moment of inertia of the circle object.
    def m_inert(self):
        return self.m * self.r**2 / 2.0
