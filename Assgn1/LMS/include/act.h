#ifndef ACT_INCLUDE
#define ACT_INCLUDE
//==================================================================
#include <iostream>
#include <vector>
#include "scene.h"
using namespace std;
class Act
{
    vector<Scene> Scenes;//List of Scenes

public:
    Act();                           //CC
    Act(const Act &other);           //CCR
    void add_sc(Scene S);            //for adding scene
    vector<Scene> getall();          //for returning all Scenes
    Act& operator=(const Act &other); //CAO
    int len();                       //for getting number of scenes
};
//====================================================================
#endif