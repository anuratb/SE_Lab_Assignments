#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
#include <dirent.h>
#include <regex>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cassert>
#include "dependencies.h"
using namespace std;
#ifndef LMS_INC
#define LMS_INC
//===================================================================================
class LMS
{
    string dir;           //Directory of Library
    vector<Book *> books; //List of Books in Library

public:
    LMS(); //CR

    void disp_contents(Book *B); //Display file contents of a books 50 lines at a time

    static void print_menu(string str); //Utility fn to print menu

    void analytics(Book *B); //to analyse current book

    void manage_chars(vector<Character> &char_list, set<string> char_name); //To preprocess all associated char in a particular scene

    void load(Novel *N); //to parse a novel file and load it

    void load(Play *P); //to parse a play file and load it

    void show_all(vector<Book *> books_todisp, string list_descrip); //to show all books by name title author in some given list

    vector<Book *> src_by_title(string Title); //search all books by title

    vector<Book *> src_by_author(string Title); //search all books by author

    string get_book_type(string book_fl); //returns book type,with its fl_name contained in fl

    Book *parse_book_file(string book_file, string book_type); //returns book,with its type contained in fl

    void update_index(); //for updating index.txt file or creating it

    void load_books();                //to load title author ,etc of each book as per index file
    static string rem_white(string &s); //to remove padded whitespace
};
//====================================================================================
#endif