#include "../include/chapter.h"
Chapter::Chapter() { this->Paragraphs = vector<Paragraph>(); }                            //CR
Chapter::Chapter(string title) : Title(title) { this->Paragraphs = vector<Paragraph>(); } //CR

Chapter::Chapter(const Chapter &other) //CCR
{
    this->Title = other.Title;
    this->Paragraphs = other.Paragraphs;
    this->chap_no = other.chap_no;
    
}
Chapter& Chapter::operator=(const Chapter &other) //CAO
{
    this->Title = other.Title;
    this->Paragraphs = other.Paragraphs;
    this->chap_no = other.chap_no;
    return *this;
}
void Chapter::set_title(string title) //for setting title
{
    this->Title = title;
}
vector<Paragraph> Chapter::get_all_para() { return this->Paragraphs; } //for getting all paragraphs
void Chapter::add(Paragraph P) //for adding a paragraph
{ 
    if(P.len()==0)  return;
    this->Paragraphs.push_back(P); 
    Paragraphs.back().set_para_no(Paragraphs.size());
    Paragraphs.back().set_chap_no(chap_no);
    Paragraphs.back().set_chap_name(this->Title);    
}      
int Chapter::len() { return this->Paragraphs.size(); }                 //for getting number of paragraphs
string Chapter::get_title() { return this->Title; }                    //for getting title