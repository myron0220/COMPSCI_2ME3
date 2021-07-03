## @file Shape.py
#  @author Mingzhe Wang (wangm235)
#  @brief A shape inerface.
#  @date 2021 Feb 16

from abc import ABC, abstractmethod


## @brief An interface specifying which methods should a Shape type has.
#  @details A Shape should have cm_x, cm_y, mass, and m_inert getters.
class Shape(ABC):

    ## @brief The implementation should get the x-coordinate of mass center.
    #  @details This method should return a real number.
    @abstractmethod
    def cm_x(self):
        pass

    ## @brief The implementation should get the y-coordinate of mass center.
    #  @details This method should return a real number.
    @abstractmethod
    def cm_y(self):
        pass

    ## @brief The implementation should get the mass.
    #  @details This method should return a real number.
    @abstractmethod
    def mass(self):
        pass

    ## @brief The implementation should get the moment of inertia.
    #  @details This method should return a real number.
    @abstractmethod
    def m_inert(self):
        pass
