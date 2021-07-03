## @file BodyT.py
#  @author Mingzhe Wang (wangm235)
#  @brief A body ADT implementing shape interface.
#  @date 2021 Feb 16

from Shape import Shape


## @brief A class representing an object consisting of finite points.
#  @details This object should implement all the methods specified in Shape interface.<br/>
#           Assumptions: The arguments provided to the access programs will be of the correct
#           type.
class BodyT(Shape):

    ## @brief Constructor for BodyT.
    #  @details Constructor taking in a sequence of x-coordinate, a sequence of y-coordinate,
    #           and a sequence of masses for an objetc consisting of finite mass points.
    #  @param xs A seq of real number representing the x-coordinates of the object's mass
    #            points.
    #  @param ys A seq of real number representing the y-coordinates of the object's mass
    #            points.
    #  @param ms A seq of real number representing the masses of the object's mass points.
    #  @throws ValueError If the three seqs do not have the same length.
    #  @throws ValueError If not all the masses of points are positive.
    def __init__(self, xs, ys, ms):
        if not (len(xs) == len(ys) == len(ms)):
            raise ValueError
        elif not self.__is_m_pos(ms):
            raise ValueError
        else:
            ## X-coordinate of the mass centre.
            self.cmx = self.__cm(xs, ms)
            ## Y-coordinate of the mass centre.
            self.cmy = self.__cm(ys, ms)
            ## Total mass of the object.
            self.m = sum(ms)
            ## Moment of inertia of the object.
            self.moment = self.__mmom(xs, ys, ms) - sum(ms) \
                * (self.__cm(xs, ms)**2 + self.__cm(ys, ms)**2)

    ## @brief Returns the x-coordinate of the centre of mass.
    #  @details The centre of mass is calculated under the physic law.
    #  @return A real number representing the x-coordinate of the centre of mass.
    def cm_x(self):
        return self.cmx

    ## @brief Returns the y-coordinate of the centre of mass.
    #  @details The centre of mass is calculated under the physic law.
    #  @return A real number representing the y-coordinate of the centre of mass.
    def cm_y(self):
        return self.cmy

    ## @brief Returns the mass of the object.
    #  @details The mass of the object is equal to the sum of masses of all points.
    #  @return A real number representing the mass of the object.
    def mass(self):
        return self.m

    ## @brief Returns the the moment of inertia of the object.
    #  @details For an object consisting of finite mass points, the moment of inertia is calc
    #           ulated using discrete quantifiers.
    #  @return A real number representing the moment of inertia of the triangle object.
    def m_inert(self):
        return self.moment

    # private method for check the positivity of a seq.
    def __is_m_pos(self, ms):
        for m in ms:
            if m <= 0:
                return False
        return True

    # private method for calculating the mass centre in one coordinate.
    def __cm(self, zs, ms):
        numerator = 0
        for i in range(len(ms)):
            numerator += zs[i] * ms[i]
        return numerator / sum(ms)

    # private method used for calculating mass moment.
    def __mmom(self, xs, ys, ms):
        quant = 0
        for i in range(len(ms)):
            quant += ms[i] * (xs[i]**2 + ys[i]**2)
        return quant
