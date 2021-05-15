
#ifndef SCENE_INCLUDE
#define SCENE_INCLUDE
//==========================================================================
#include <iostream>
#include <vector>
#include "dialogue.h"
using namespace std;
class Scene
{
    vector<Dialogue> Dialogues; //List of Dialogues

public:
    Scene();                              //CR
    Scene(const Scene &other);            //CCR
    vector<Dialogue> get_all();           //returns all dialogues
    int len();                            // ret no. of dialogues
    void add(Dialogue D);                 //for adding a dialogue
    Dialogue get(int ind);                //for getting dialogue at ind
    Scene &operator=(const Scene &other); //CAO
};
//==========================================================================
#endif