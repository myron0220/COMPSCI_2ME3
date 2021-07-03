## @file BodyT.py
#  @author Steven Kostiuk
#  @brief Contains the BodyT type to represent physics bodies
#  @date 2021-02-16
from Shape import Shape

## @brief BodyT is used to represent a combination of shapes
#  that form a physics body


class BodyT(Shape):
    ## @brief Constructor for class TriangleT
    #  @param xs List of x values of the center of mass
    #  @param ys List of Y values of the center of mass
    #  @param ms List of masses
    #  @throws ValueError Thrown if the lengths of the 3 lists are not the same
    #  @throws ValueError Thrown if one of the masses in the list of masses
    #  is less than or equal to zero
    def __init__(self, xs, ys, ms):
        if not(len(xs) == len(ys) == len(ms)):
            raise ValueError
        for u in ms:
            if not(u > 0):
                raise ValueError
        self.cmx = self.__cm(xs, ms)
        self.cmy = self.__cm(ys, ms)
        self.m = self.__sum(ms)
        self.moment = self.__mmom(xs, ys, ms) - self.__sum(ms) * \
            (self.__cm(xs, ms) ** 2 + self.__cm(ys, ms) ** 2)

    ## @brief Getter method that gets the x value of the center of mass
    #  @return x value of the center of mass
    def cm_x(self):
        return self.cmx

    ## @brief Getter method that gets the y value of the center of mass
    #  @return y value of the center of mass
    def cm_y(self):
        return self.cmy

    ## @brief Getter method that gets the mass of the physics body
    #  @return mass of the physics body
    def mass(self):
        return self.m

    ## @brief Getter method that gets the moment of inertia of the physics body
    #  @return moment of inertia of the physics body
    def m_inert(self):
        return self.moment

    ## @brief Calculates the sum of a list
    #  @param m List of masses
    #  @return total mass of the physics body
    def __sum(self, m):
        result = 0
        for i in m:
            result = result + i
        return result

    ## @brief Calculates the center of mass of the physics body
    #  @param z List of coordinates that represent the x or y values of center of mass
    #  @param m List of masses
    #  @returns x or y coordinate of the center of mass of the physics body
    def __cm(self, z, m):
        result = 0
        for i in range(len(m)):
            result = result + z[i] * m[i]
        return result / self.__sum(m)

    ## @brief Calculates the first part of the moment of inertia of the physics body
    #  @param x List of x values of the center of mass
    #  @param y List of y values of the center of mass
    #  @param m List of masses
    #  @return first part of the moment of inertia of the physics body
    def __mmom(self, x, y, m):
        result = 0
        for i in range(len(m)):
            result = result + m[i] * (x[i] ** 2 + y[i] ** 2)
        return result
