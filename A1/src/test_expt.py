# Sample tests for minimal experiments with the ADT interfaces
# The purpose of this file is to give some examples that will help
# with completing the assignment. In particular, examples are given to
# exercise the correct interface.  This should help us prevent the
# frustrating (for everyone!) errors where the syntax of the
# specification is not followed.

# This file may be updated if student questions suggest that another
# example would be helpful.  Therefore, you should avoid writing any
# code in this file that you may want to keep.  If you change this
# file, your changes could be lost when we force push a new version.

from complex_adt import ComplexT
from triangle_adt import TriangleT, TriType

# Sample manually written test case
# Test of add method
a = ComplexT(1.0, 2.0)
b = ComplexT(0.5, -0.5)
c = a.add(b)
if ((c.equal(ComplexT(1.5, 1.5)))):
   print("add test passes")
else:
   print("add test FAILS")

# The manual test cases could be made fancier, by adding counters to
# count the total number of tests, passed tests and failed tests.
# Test cases can be added to verify exceptions are generated, if
# you add exceptions to the ADT interface.

# Below are incomplete tests - their purpose is to exercise the
# interface, so that your code will least matches the specified
# interface syntax

# ComplexT Interface Syntax
a.real()
a.imag()
a.get_r()
a.get_phi()
a.equal(b)
a.conj()
a.add(b)
a.sub(b)
a.mult(b)
a.recip()
a.div(b)
a.sqrt()

# TriangleT
t1 = TriangleT(3, 4, 5)
t2 = TriangleT(4, 3, 5)

t1.get_sides()

# Test of equal method
if (t1.equal(t2)):
   print("triangle equal test passes")
else:
   print("triangle equal test FAILS")

t1.perim()
t1.area()
t1.is_valid()

# Test triangle type
if (t1.tri_type() == TriType.right):
   print("tri_type test passes")
else:
   print("tri_type test FAILS")


