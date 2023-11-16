all: run

ifndef ANTLR4
$(error variable ANTLR4 is not set)
endif

#adapt if necessary
GRUN=/usr/share/antlr4/grun

Slide18Parser.class: Slide18.g4
	$(ANTLR4) -no-listener $<
	javac Slide18*.java

run: Slide18Parser.class
	$(GRUN) Slide18 start -gui


clean:
	rm -f *er.py *.interp *.tokens *.java *.class *~
	rm -Rf __pycache__
