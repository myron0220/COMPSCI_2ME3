## @file SetOfInt.py
#  @author Mingzhe Wang
#  @brief Set of integers
#  @date 03/04/2021

class SetOfInt:
    
    def __init__(self, xs):
        self.s = set(xs)
        
    def is_member(self, x):
        return x in self.s
    
    def to_seq(self):
        return list(self.s)
    
    def union(self, t):
        s = self.s.copy()
        for x in list(t.__gets()):
            s.add(x)
        return SetOfInt(list(s))
    
    def diff(self, t):
        return SetOfInt(list(self.s - t.__gets()))
    
    def size(self):
        return len(self.s)
    
    def empty(self):
        return len(self.s) == 0
    
    def equals(self, t):
        return self.s == t.__gets()
    
    def __gets(self):
        return self.s
    
    