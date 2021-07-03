PY = python3
PYFLAGS = 
PYTEST = pytest
PYTESTFLAGS = --cov

SRC = src
SRCEXPT = src/expt.py

RMDIR = rm -rf

.PHONY: test expt clean

test:
	$(PYTEST) $(PYTESTFLAGS) $(SRC)

expt: 
	$(PY) $(PYFLAGS) $(SRCEXPT)

clean:
	@- $(RMDIR) src/__pycache__
