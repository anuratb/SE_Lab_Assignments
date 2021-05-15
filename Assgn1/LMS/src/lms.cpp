#include "../include/lms.h"

string dir;
vector<Book *> books;

LMS::LMS() //CR
{
    books = vector<Book *>();
    cout << "Enter Directory Address : ";
    cin >> dir;
    cout << "Starting Library Management System(LMS)...\n";
    update_index();
    load_books();
    print_menu("Book");
    while (1)
    {
        cout << "Enter Option(Enter 4 to print Main Menu again) : ";
        int option;
        cin >> option;
        if (option == 1) //showing all books
        {
            show_all(books, "Showing all Available Books");
            if (books.size() == 0)
            {
                continue;
            }
            cout << "Enter Sl No. of Selected book: ";
            int id;
            cin >> id;
            id--;
            if (id < 0 || id > (int)books.size())
            {
                cout << "Invalid sl No. entered,Aborting current process\n";
                continue;
            }
            disp_contents(books[id]); //Displaying book
            analytics(books[id]);     //Analysing the book
        }
        else if (option == 2) //searching  by title
        {
            string src_title;
            cout << "Enter keyword to serch in Title: ";
            cin >> src_title;
            vector<Book *> curr_list;
            show_all(curr_list = src_by_title(src_title), "Searching By Title: ");
            if (curr_list.size() == 0)
            {
                continue;
            }
            cout << "Enter Sl No. of Selected book: ";
            int id;
            cin >> id;
            id--;
            if (id < 0 || id > (int)curr_list.size())
            {
                cout << "Invalid sl No. entered,Aborting current process\n";
                continue;
            }
            disp_contents(curr_list[id]); //displaying book
            analytics(curr_list[id]);     //Analysing the book
        }
        else if (option == 3) //Searching by author
        {
            string src_auth;
            cout << "Enter keyword to serch in Author: ";
            cin >> src_auth;
            vector<Book *> curr_list;
            show_all(curr_list = src_by_author(src_auth), "Searching By Author: ");
            if (curr_list.size() == 0)
            {
                continue;
            }
            cout << "Enter Sl No. of Selected book: ";
            int id;
            cin >> id;
            id--;
            if (id < 0 || id > (int)curr_list.size())
            {
                cout << "Invalid sl No. entered,Aborting current process\n";
                continue;
            }
            disp_contents(curr_list[id]); //Displaying book
            analytics(curr_list[id]);     //Analysing the book
        }
        else if (option == 0)
        {
            break;
        }
        else if (option == 4)
        {
            print_menu("Book");
        }
        else
        {
            cout << "Enter valid option" << endl;
        }
    }
}
void LMS::disp_contents(Book *B)
{
    ifstream fin;
    string str(dir);
    str.append(B->get_fl_nme());
    fin.open(str.c_str(), ios::in);
    string line;
    int x = 1;
    cout << "\nBook : " << B->get_title() << " by " << B->get_auth() << " Release Date: " << B->get_date() << endl;

    cout << endl;
    cout << "Displaying current Books Contents,50 lines at a time\n";
    while (x == 1)
    {
        for (int i = 0; i < 50; i++)
        {
            getline(fin, line);
            cout << line << endl;
        }
        cout << "Do you want to print more(Enter 1 for yes, 0 for no: ";
        cin >> x;
    }
}
void LMS::print_menu(string str)
{
    if (str.compare("Book") == 0)
    {
        cout << "Menu:\n";
        cout << "Enter 1 to show all books:\n";
        cout << "Enter 2 to search by Title:\n";
        cout << "Enter 3 to search by author:\n";
        cout << "Enter 4 to print Main Menu again:\n";
        cout << "Enter 0 to exit\n";
    }
    else if (str.compare("Play") == 0)
    {
        cout << "    Play Sub Menu:\n";
        cout << "    Enter 1 to print all characters ,some character is associated with\n";
        cout << "    Enter 2 to print Play Sub Menu again:\n";
        cout << "    Enter 0 to go back to main Menu\n";
    }
    else
    {
        cout << "    Novel Sub Menu:\n";
        cout << "    Enter 1 to print top chapters having most frequency of search word:\n";
        cout << "    Enter 2 to print top paragraphs having most frequency of search word:\n";
        cout << "    Enter 3 to print the Novel Sub Menu again:\n";
        cout << "    Enter 0 to go back to main Menu\n";
    }
}
void LMS::analytics(Book *B)
{
    cout << "Loading Current Book...\n";
    if (B->get_type().compare("Play") == 0)
    {
        load(dynamic_cast<Play *>(B)); //Play data
    }
    else
    {
        load(dynamic_cast<Novel *>(B)); //Novel Data
    }
    if (B->get_type().compare("Play") == 0) //For Play Type
    {
        print_menu("Play");
        int x;
        while (1)
        {
            cout << "    Enter Play Option(Enter 2 to print submenu again): ";
            cin >> x;
            if (x == 1)
            {
                string nme;
                cout << "    Enter Character Name: ";
                cin >> nme;
                dynamic_cast<Play *>(B)->print_asso_char(nme);
            }
            else if (x == 2)
            {
                print_menu("Play");
            }
            else if (x == 0)
            {
                break;
            }
            else
            {
                cout << "    Enter Valid Option\n";
            }
        }
    }
    else
    {
        Novel *N = dynamic_cast<Novel *>(B);
        print_menu("Novel");
        int x;
        cout << "    Enter Novel Option: ";
        cin >> x;
        while (x > 0)
        {
            if (x == 1)
            {
                cout << "    Enter number of top chapters to be displayed: ";
                int k;
                cin >> k;
                cout << "    Enter word/substring to be searched: ";
                string str;
                cin >> str;
                vector<Chapter *> chaps(N->len());
                for (int i = 0; i < N->len(); i++)
                {
                    chaps[i] = new Chapter(N->get(i));
                }
                if (k > (int)chaps.size())
                {
                    cout << "Sorry!Only " << chaps.size() << " Chapters Available,\n";
                    continue;
                }
                sort(chaps.begin(), chaps.end(), ByWordCnt(str));
                cout << "    Top " << k << " Chapters containing " << str << ", in descending order are:\n";
                for (int i = 0; i < k; i++)
                {
                    string nme = chaps[i]->get_title();
                    cout << "    Chapter " << chaps[i]->get_chap_no() << " : " << rem_white(nme) << endl;
                }
            }
            else if (x == 2)
            {
                cout << "    Enter number of top Paragraphs to be displayed: ";
                int k;
                cin >> k;
                cout << "    Enter word/substring to be searched: ";
                string str;
                cin >> str;
                vector<Paragraph *> paras;
                for (Chapter C : N->get_all_chap())
                {
                    for (Paragraph P : C.get_all_para())
                    {
                        paras.push_back(new Paragraph(P));
                    }
                }
                if (k > (int)paras.size())
                {
                    cout << "Sorry!Only " << paras.size() << " Paras Available,\n";
                    continue;
                }
                // for (Paragraph *P : paras)
                // {
                //     for (Sentence S : P->get_all())
                //     {
                //         for (string word : S.get_all())
                //         {
                //            cout << "6::" << word << endl; //DEBUG
                //        }
                //    }
                //}
                sort(paras.begin(), paras.end(), ByWordCnt(str));
                cout << "    Top " << k << " Paragraphs containing " << str << ", in descending order are:\n";
                for (int i = 0; i < k; i++)
                {
                    string str = paras[i]->get_chap_nme();
                    rem_white(str);
                    cout << "    Chapter " << paras[i]->get_chap_no() << " : " << str << " ,Paragraph " << paras[i]->get_para_no() << endl;
                }
            }
            else if (x == 3)
            {
                print_menu("Novel");
            }
            else if (x == 0)
            {
                break;
            }
            else
            {
                cout << "    Enter valid option" << endl;
            }

            cout << "    Enter Novel Option(3 to print sub menu again): ";
            cin >> x;
        }
    }
}
void LMS::manage_chars(vector<Character> &char_list, set<string> char_name) //To preprocess all associated char
{

    for (string str : char_name)
    {
        if (char_list.size() == 0) //Intially char_list may be empty
        {
            char_list.push_back(Character(str));
            //Add all other charactors of current scene to set of current characters associated characters
            for (string other_char : char_name)
            {
                if (other_char.compare(str) == 0)
                {
                    continue;
                }
                char_list[0].add_co(other_char);
                assert(char_list[0].get_name().compare(other_char) != 0);
            }
            continue;
        }
        for (int i = 0; i < ((int)char_list.size()); i++)
        {
            if (char_list[i].get_name().compare(str) == 0)
            {
                //Add all other charactors of current scene to set of current characters associated characters
                for (string other_char : char_name)
                {
                    if (other_char.compare(str) == 0)
                    {
                        continue;
                    }
                    char_list[i].add_co(other_char);
                    assert(char_list[i].get_name().compare(other_char) != 0);
                }
                break;
            }
            else if (i == (int)char_list.size() - 1)
            {
                char_list.push_back(Character(str));
                i++;
                for (string other_char : char_name)
                {
                    if (other_char.compare(str) == 0)
                    {
                        continue;
                    }
                    char_list[i].add_co(other_char);
                }
            }
        }
    }
}

void LMS::load(Novel *N)
{
    regex pat("CHAPTER .*.*");
    ifstream fin;
    string str(dir);
    str.append(N->get_fl_nme());
    fin.open(str.c_str(), ios::in);
    string line;
    Chapter *curr_chapter = NULL;
    Paragraph *curr_para = NULL;
    Sentence *curr_sentence = NULL;

    while (getline(fin, line))
    {
        rem_white(line);
        // cout << "0::" << line << endl; //DEBUG
        stringstream ss(line);
        stringstream s2(line);
        string t1;
        s2 >> t1;
        if (strcmp(t1.c_str(), "CHAPTER") == 0)
        {
            // cout << "1::" << line << endl; //DEBUG
            string buf;
            getline(ss, buf, '.');
            string nme;
            getline(ss, nme);
            if (nme.size() == 0)
            {
                getline(fin, nme);
                // cout << "2::" << nme << endl; //DEBUG
            }
            curr_para = new Paragraph;
            curr_sentence = new Sentence;
            curr_chapter = new Chapter(nme);
            curr_chapter->set_chap_no(N->len() + 1);
            while (getline(fin, line))
            {
                rem_white(line);
                // cout << "3::" << line << endl; //DEBUG
                ss = stringstream(line);
                stringstream s3(line);
                string t2;
                s3 >> t2;
                if ((strcmp(t2.c_str(), "CHAPTER") == 0))
                {
                    //cout << "5::" << endl; //DEBUG
                    //new chapter
                    curr_para->add_Sentence(*curr_sentence);
                    curr_chapter->add(*curr_para);
                    N->add_chap(*curr_chapter);
                    delete curr_sentence;
                    delete curr_para;
                    delete curr_chapter;
                    string buf;
                    getline(ss, buf, '.');
                    string nme;
                    getline(ss, nme);
                    if (nme.size() == 0)
                    {
                        getline(fin, nme);
                    }
                    curr_chapter = new Chapter(nme);
                    curr_chapter->set_chap_no(N->len() + 1);
                    curr_para = new Paragraph;
                    curr_sentence = new Sentence;
                    continue;
                }
                stringstream s1(line);
                string temp;
                s1 >> temp;
                if (temp.size() == 0)
                {
                    //end of curr_para
                    // cout << "4::" << endl; //DEBUG
                    curr_para->add_Sentence(*curr_sentence);
                    curr_chapter->add(*curr_para);
                    delete curr_sentence;
                    delete curr_para;
                    curr_para = new Paragraph;
                    curr_sentence = new Sentence;
                    continue;
                }
                string word;
                while (ss >> word)
                {
                    curr_sentence->add_word(word);
                    if (word.back() == '.' || word.back() == '?')
                    {
                        //end of sentence
                        curr_para->add_Sentence(*curr_sentence);
                        delete curr_sentence;
                        curr_sentence = new Sentence;
                    }
                }
            }
            curr_para->add_Sentence(*curr_sentence);
            curr_chapter->add(*curr_para);
            N->add_chap(*curr_chapter);
            delete curr_sentence;
            delete curr_para;
            delete curr_chapter;
        }
    }
    // cout << N->len() << endl; //DEBUG
    //for (Chapter C : N->get_all_chap())
    //{
    //    cout << C.get_chap_no() << " " << C.get_title() << endl; //DEBUG
    // }

    fin.close();
}
void LMS::load(Play *P)
{
    //cout << "Entered Load\n"; //DEBUG
    regex pat1("(.*)\\[(.*)\\[(.*)");
    regex pat2("([A-Z]*)");
    //cout << "TEST:" << regex_match("FLAVIUS. fgdf", pat2) << endl; //DEBUG
    ifstream fin;
    string str(dir);
    str.append(P->get_fl_nme());
    fin.open(str.c_str(), ios::in);
    string line;

    //bool str_scn = 0; //Indicates start of scene

    vector<Character> chars;
    set<string> curr_scn_chars;
    while (getline(fin, line))
    {
        if (line.find("SCENE") != string::npos)
        {
            //cout << line << endl; //DEBUG
            while (getline(fin, line))
            {

                if (line.find("[_Exeunt._]") != string::npos)
                {
                    //cout << "0::" << line << endl; //DEBUG
                    manage_chars(chars, curr_scn_chars);
                    //cout << chars.size() << " " << curr_scn_chars.size() << endl; //DEBUG
                    curr_scn_chars.clear();
                    break;
                }
                stringstream ss(line);
                string nme;
                getline(ss, nme, '.');
                if (regex_match(nme, pat2))
                {
                    //cout << "1::" << line << endl; //DEBUG
                    curr_scn_chars.insert(nme);
                }
                else
                {
                    //cout << "3::\n";      //DEBUG
                    //cout << line << endl; //DEBUG
                }
            }
        }
    }
    for (Character itr : chars)
        P->add_char(itr);

    //for (Character itr : chars)
    //   cout << itr.get_name() << endl; //DEBUG
    fin.close();
}
void LMS::show_all(vector<Book *> books_todisp, string list_descrip) //to show all books by name title author in some given list
{
    if (books_todisp.size() == 0)
    {
        cout << "Sorry No Book found by " << list_descrip << endl;
        return;
    }
    cout << list_descrip << endl;
    cout << "Sl_no   Book File Name                Title                        Author\n";
    cout << "-------------------------------------------------------------------------------\n";
    for (int i = 0; i < ((int)books_todisp.size()); i++)
    {
        Book *itr = books_todisp[i];
        cout << setw(8) << left << i + 1;
        cout << setw(28) << left << itr->get_fl_nme();
        cout << setw(28) << left << itr->get_title() << setw(28) << left << itr->get_auth() << endl;
    }
}
vector<Book *> LMS::src_by_title(string Title) //search all books by title
{
    vector<Book *> src_res;
    for (int i = 0; i < ((int)books.size()); i++)
    {
        Book *itr = books[i];
        if (strcasestr(itr->get_title().c_str(), Title.c_str()) != NULL)
        {
            src_res.push_back(itr);
        }
    }
    return src_res;
}
vector<Book *> LMS::src_by_author(string Title) //search all books by author
{
    vector<Book *> src_res;
    for (int i = 0; i < ((int)books.size()); i++)
    {
        Book *itr = books[i];
        if (strcasestr(itr->get_auth().c_str(), Title.c_str()) != NULL)
        {
            src_res.push_back(itr);
        }
    }
    return src_res;
}
string LMS::get_book_type(string book_fl) //returns book type,with its fl_name contained in fl
{
    cout << "Enter type of book with file name, " << book_fl << " : ";
    string book_type;
    cin >> book_type;
    return book_type;
}
Book *LMS::parse_book_file(string book_file, string book_type) //returns book,with its type contained in fl
{
    //opening the books file
    string book_file_path = string(dir);
    book_file_path.append(book_file);
    ifstream fin;
    fin.open(book_file_path.c_str(), ios::in);
    string title, author, release_date, lang;
    title = author = release_date = lang = "";
    string line;
    //reading the books file line by line
    while (getline(fin, line))
    {
        stringstream ss(line); //get a line
        string label;
        if (line.find("Title:") != string::npos)
        {
            getline(ss, label, ':');
            getline(ss, title);
            rem_white(title);
        }
        else if (line.find("Author:") != string::npos)
        {
            getline(ss, label, ':');
            getline(ss, author);
            rem_white(author);
        }
        else if (line.find("Release Date:") != string::npos)
        {
            getline(ss, label, ':');
            getline(ss, release_date, '[');
            rem_white(release_date);
        }
        else if ((line.find("Lang:") != string::npos) || (line.find("Language:") != string::npos))
        {
            getline(ss, label, ':');
            getline(ss, lang);
            rem_white(lang);
        }
    }
    //Reporting errors encountered
    if (title.size() == 0)
    {
        cerr << "Error! " << book_file << " has no Title field,so its kept empty\n";
    }
    else if (author.size() == 0)
    {
        cerr << "Error! " << book_file << " has no Author field,so its kept empty\n";
    }
    else if (release_date.size() == 0)
    {
        cerr << "Error! " << book_file << " has no Release-date field,so its set to current date\n";
    }
    else if (lang.size() == 0)
    {
        cerr << "Error! " << book_file << " has no Lang field,so its kept empty\n";
    }

    if (book_type == "Play")
    {
        return new Play(title, author, release_date, lang, book_file);
    }
    else
    {
        return new Novel(title, author, release_date, lang, book_file);
    }
}
void LMS::update_index() //for updating index.txt file or creating it
{
    DIR *dr;
    struct dirent *en;
    if (dir[(int)(dir.size()) - 1] != '/')
        dir.append("/");
    dr = opendir(dir.c_str()); //opening directory,where books are saved
    if (dr)
    {

        ifstream fin;
        string str(dir);
        str.append("index.txt");
        fin.open(str.c_str(), ios::in); //opening index.txt
        vector<string> book_list;
        if (fin) //Adding those values present in index to a vector
        {
            string curr_line;
            while (getline(fin, curr_line))
            {
                stringstream ss(curr_line);
                string book_fl_nme;
                getline(ss, book_fl_nme, ',');
                book_list.push_back(book_fl_nme);
            }
        }
        fin.close();
        fstream fout;
        fout.open(str.c_str(), ios::app);
        //compute list of books in directory dir
        while ((en = readdir(dr)) != NULL)
        {
            string curr_file = en->d_name;
            if (curr_file.find(".txt") != string::npos)
            {
                if (find(book_list.begin(), book_list.end(), curr_file) == book_list.end()) //curr_file not found in index.txt
                {
                    //add it to index.txt
                    if (curr_file.compare("index.txt") == 0)
                        continue;
                    string book_type = get_book_type(curr_file);
                    fout << curr_file << "," << book_type << endl;
                }
            }
        }
        fout.close();
        closedir(dr);
    }
}
void LMS::load_books()
{
    cout << "Loading Books from Library Directory...\n";
    ifstream fin;
    string str(dir);
    str.append("index.txt");
    fin.open(str.c_str(), ios::in);
    string line;
    while (getline(fin, line))
    {
        stringstream ss(line);
        string book_file;
        string book_type;
        getline(ss, book_file, ',');
        getline(ss, book_type);
        Book *curr_book = parse_book_file(book_file, book_type);
        this->books.push_back(curr_book);
    }
}
string LMS::rem_white(string &s)
{
    if (s.size() == 0)
        return s;
    int x = s.back();
    while (isspace(x))
    {
        s.pop_back();
        if (s.size() == 0)
            return s;
        x = s.back();
    }
    string nw;
    int i = 0;
    while ((i < (int)s.size()) && (isspace(s[i])))
    {
        i++;
    }
    for (; i < (int)s.size(); i++)
    {
        nw.push_back(s[i]);
    }
    s = nw;
    return s;
}