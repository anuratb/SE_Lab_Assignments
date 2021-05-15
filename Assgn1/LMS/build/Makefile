IDIR = ../include/

SDIR = ../src/
CC = g++
CFLAGS = -Wall


all : main.o book.o novel.o play.o dialogue.o scene.o act.o chapter.o character.o paragraph.o sentence.o comparator.o lms.o	
	$(CC) $(CFLAGS) -o main main.o book.o novel.o play.o dialogue.o scene.o act.o chapter.o character.o paragraph.o sentence.o comparator.o lms.o
clean_lms :
	rm lms.o
lms.o : book.o novel.o play.o dialogue.o scene.o act.o chapter.o character.o paragraph.o sentence.o comparator.o
	$(CC) $(CFLAGS) -c $(SDIR)lms.cpp
main.o : lms.o
	$(CC) $(CFLAGS) -c ../main.cpp
comparator.o : novel.o chapter.o paragraph.o sentence.o
	$(CC) $(CFLAGS) -c $(SDIR)comparator.cpp
book.o : 
	$(CC) $(CFLAGS) -c $(SDIR)book.cpp
dialogue.o : 	
	$(CC) $(CFLAGS) -c $(SDIR)dialogue.cpp
scene.o : dialogue.o 
	$(CC) $(CFLAGS) -c $(SDIR)scene.cpp 
	
act.o : scene.o
	$(CC) $(CFLAGS) -c $(SDIR)act.cpp 

chapter.o : paragraph.o
	$(CC) $(CFLAGS) -c $(SDIR)chapter.cpp 
character.o : 
	$(CC) $(CFLAGS) -c $(SDIR)character.cpp
novel.o : book.o chapter.o
	$(CC) $(CFLAGS) -c $(SDIR)novel.cpp 
paragraph.o : sentence.o 
	$(CC) $(CFLAGS) -c $(SDIR)paragraph.cpp 
play.o : book.o act.o character.o
	$(CC) $(CFLAGS) -c $(SDIR)play.cpp 
sentence.o : 
	$(CC) $(CFLAGS) -c $(SDIR)sentence.cpp
clean :
	rm *.o


