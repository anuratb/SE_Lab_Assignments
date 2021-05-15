#include "../include/paragraph.h"
Paragraph::Paragraph() {}                    //CR
Paragraph::Paragraph(const Paragraph &other) //CCR
{
    this->Sentences = other.Sentences;
    this->chap_no = other.chap_no;
    this->para_no = other.para_no;
    this->chap_name = other.chap_name;
}
Paragraph &Paragraph::operator=(const Paragraph &other) //CAO
{
    this->Sentences = other.Sentences;
    this->chap_no = other.chap_no;
    this->para_no = other.para_no;
    this->chap_name = other.chap_name;
    return *this;
}
void Paragraph::add_Sentence(Sentence S) //for adding Sentence
{
    if (S.len() == 0)
        return;
    Sentences.push_back(S);
}
vector<Sentence> Paragraph::get_all() { return Sentences; } //for getting all sentences
int Paragraph::len() { return Sentences.size(); }           //for getting no. of sentences
Sentence Paragraph::get(int ind) { return Sentences[ind]; } //for getting sentence at index ind(0 indexed)