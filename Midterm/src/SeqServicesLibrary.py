## @file SeqServicesLibrary.py
#  @author Mingzhe Wang
#  @brief Library module that provides functions for working with sequences
#  @details This library assumes that all functions will be provided with arguments of the expected types
#  @date 03/04/2021

from functools import *

def max_val(s):
    if len(s) == 0:
        raise ValueError
    mval = abs(s[0])
    for x in s:
        if abs(x) > mval:
            mval = abs(x)
    return mval

def count(t, s):
    if len(s) == 0:
        raise ValueError
    return reduce(lambda x, y: x + y, [1 for x in s if x == t], 0)

def spices(s):
    if len(s) == 0:
        raise ValueError
    return ["nutmeg" if x <= 0 else "ginger" for x in s]

def new_max_val(s, f):
    if len(s) == 0:
        raise ValueError
    return max_val([x for x in s if f(x)])