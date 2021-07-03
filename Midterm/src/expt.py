## @file expt.py
#  @author Spencer Smith
#  @brief This file is intended to help test that your interface matches the specified interface
#  @date 03/04/2021

from SeqServicesLibrary import *
from SetOfInt import *

# Exercising Sequence Services Library
print()
print("SeqServicesLibrary, max_val expt:", max_val([1, 2, -3]))
print("SeqServicesLibrary, count expt:", count(1, [1, 1, 1, 1]))
print("SeqServicesLibrary, spices expt:", spices([-5, 0, 23, -.01]))
print("SeqServicesLibrary, new_max_val expt:", new_max_val([-5, 0, 23, 10], lambda x: x == 10))
print()

# Exercising Set of Integers
xs = [-9, 6, 23, 21, -5]
ys = list(xs)
ys.append(99)
S = SetOfInt(xs)
print("SetOfInt, is_member expt:", S.is_member(21))
print("SetOfInt, to_seq expt:", S.to_seq())
S2 = SetOfInt(ys)
S3 = S.union(S2)
print("SetOfInt, union expt:", S3.to_seq())
S4 = S2.diff(S)
print("SetOfInt, diff expt:", S4.to_seq())
print("SetOfInt, size expt:", S4.size())
print("SetOfInt, size expt:", S4.empty())
S5 = SetOfInt([-9, 6, 23, -5, 21])
print("SetOfInt, equals expt:", S.equals(S5))
print()

S1 = SetOfInt([1,9,3,200,81,21,99,12])
S2 = SetOfInt([3,1,9,10])
S3 = S1.diff(S2)
S5 = SetOfInt([21,81,200])



print("SetOfInt, union expt:", S3.to_seq())
print("SetOfInt, size expt:", S3.size())
print("SetOfInt, size expt:", S3.empty())
print("SetOfInt, equals expt:", S3.equals(S5))
print(type(S2))
