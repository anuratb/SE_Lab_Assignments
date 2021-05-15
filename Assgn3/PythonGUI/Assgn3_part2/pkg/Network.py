# 19CS10071
# Anurat Bhattacharya
from pkg.User import *
from pkg.Group import *


class Network:
    '''
    __Users : Dict contaning all users indexed by their id
    __Groups :Dict Containing all groups indexed by their id
    '''
    def __init__(self):
        self.__Users = {}
        self.__Groups = {}
        self.load()  # Load the _Users and _Groups

    def nUsr(self):   #returns no. of users
        return len(self.__Users)

    def getUsers(self):  # returns dictionary of users
        return self.__Users

    def getGrps(self):  # return dict of groups
        return self.__Groups

    def getUsrNames(self):  # returns list of user names
        return [key for key, val in self.__Users.items()]

    def getGrpsNames(self):  # returns list of group names
        return [key for key, val in self.__Groups.items()]

    def load(self):    #Parse the member initialisation and message file and load everything
        # Read Members
        ipfile = open("data/data.txt", "r")
        curr_line = ipfile.readline().strip('\n')
        while (curr_line != "#users"):
            curr_line = ipfile.readline().strip('\n')
        curr_line = ipfile.readline().strip('\n')
        while (curr_line != "#groups"):
            usr_id = curr_line.strip('<').split(':')[0]
            if (usr_id not in self.__Users):
                self.__Users[usr_id] = User(usr_id)
            curr_usr = self.__Users[usr_id]
            for x in curr_line.split(':')[1].split('>')[0].split(','):
                _x = x.strip()
                if (len(_x) > 0):
                    if _x in self.__Users:
                        curr_usr.addCont(self.__Users[_x])
                    else:
                        self.__Users[_x] = User(_x)
                        curr_usr.addCont(self.__Users[_x])

            curr_line = ipfile.readline().strip('\n')

        curr_line = ipfile.readline().strip('\n')
        while (len(curr_line) > 0):
            grp_id = curr_line.strip('<').split(':')[0]
            if(grp_id not in self.__Groups):
                self.__Groups[grp_id] = Group(grp_id)
            curr_grp = self.__Groups[grp_id]
            for x in curr_line.split(':')[1].split('>')[0].split(','):
                _x = x.strip()
                try:
                    if (len(_x) > 0):
                        curr_grp.addMem(self.__Users[_x])
                        self.__Users[_x].addGrp(curr_grp)
                except:
                    print('Error in member Initialisation from file')
            curr_line = ipfile.readline().strip('\n')

        ipfile.close()
        # read Messages
        ipfile = open("data/message.txt", 'r')
        curr_line = ipfile.readline()

        '''
        format : <from,to,<Grp/Cont>>
                 Message
                 ...
                 <end>
        '''
        while (len(curr_line) > 0):
            img_path=''
            #print('1::' + curr_line)  #DEBUG
            curr_line = curr_line.strip()
            curr_line = curr_line.strip('<')
            curr_line = curr_line.strip('>')
            #print('2::' +curr_line)  #DEBUG
            frm, to, mode = curr_line.split(',')
            frm.strip()
            to.strip()
            mode.strip()
            mess = ''
            curr_line = ipfile.readline()
            while(len(curr_line)>0 and curr_line.strip()!='<end>'):
                if(curr_line.find('img:')!=-1):
                    img_path = curr_line.split(':')[1].strip()
                else:
                    mess = mess + curr_line
                curr_line = ipfile.readline()
            #print('3::'+mess)   #DEBUG
            #print('4::')#Debug
            if (mode == 'Cont'):
                self.dirMsg(frm, to, mess,img_path)
            elif (mode == "Grp"):
                self.grpMes(frm, to, mess,img_path)
            curr_line = ipfile.readline().strip()
        ipfile.close()

    #Sending a Direct Message
    def dirMsg(self, id, to, mess,img_path='', wrt=False):
        sender = None
        receiver = None
        if (id in self.__Users):
            sender = self.__Users[id]
        if (to in self.__Users):
            receiver = self.__Users[to]
        if receiver not in sender.getContList():
            raise ValueError
        try:
            sender.addSent(((mess, img_path), "Cont", to))
            receiver.addRecvMess(((mess, img_path), "Cont", id))
        except:
            print('Error in Member Initialisation File')

        if (wrt):
            opfile = open("data/message.txt", 'a')
            opfile.write('<' + str(id) + ',' + str(to) + ',' + 'Cont'+ '>\n')
            opfile.write(mess)
            opfile.write('img:'+img_path+'\n')
            opfile.write('<end>\n')
            opfile.close()

    #Sending a Grp Message
    def grpMes(self, id, grp_id, mess,img_path='', wrt=False):
        sender = None
        recv = None
        if id in self.__Users:
            sender = self.__Users[id]
        if grp_id in self.__Groups:
            recv = self.__Groups[grp_id]
        if recv not in sender.getGrpList():
            raise ValueError
        try:
            sender.addSent(((mess,img_path), "Grp", grp_id))
            recv.addMess((mess, id))
        except:
            print('Error in the file,for member initialisation')

        for usr in recv.getMem():
            usr.addRecvMess(((mess, img_path), "Grp", id, grp_id))

        if (wrt):
            opfile = open("data/message.txt", 'a')
            opfile.write('<' + str(id) + ',' + str(grp_id) + ',' + 'Grp' + '>\n')
            opfile.write(mess)
            opfile.write('img:'+img_path+'\n')
            opfile.write('<end>\n')
            opfile.close()
