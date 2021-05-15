# 19CS10071
# Anurat Bhattacharya
from datetime import datetime
from PIL import ImageTk
import PIL.Image
import shutil
from tkinter import *
from tkinter import ttk, messagebox,filedialog
from pkg.Network import *



#To select User
class UserSelect(Frame):
    def __init__(self,master = None,Net:Network = None):
        Frame.__init__(self,master,height='80',width='250')
        self.master = master
        self.Label1 = Label(self,text='Select User',width=25,font = ('Arial',12))
        self.Label1.grid(row=0,column=0,sticky='nsew')
        self.selectUser = ttk.Combobox(self, height=4, width=26, textvariable=Net.nUsr(),font = ('Arial',12),state='readonly')
        self.selectUser['values'] = Net.getUsrNames()
        self.selectUser.grid(row=1, column=0, sticky='nsew')
        self.grid(row=0,column=0,sticky="nsew")
        self.selectUser.bind('<<ComboboxSelected>>',self.updateUser)
    def updateUser(self,event):
        self.master.changeUser(self.selectUser.get())
        pass

    def getUsr(self):
        return self.selectUser.get()
# TO display list of unread message
class RecvMess(Frame):
    def __init__(self, master=None,Net:Network=None):
        Frame.__init__(self, master, bg='#FF9933', height='750', width='650')
        self.master = master
        self.grid(row=0, column=0, sticky="nsew")
        self.currUsr = None
        self.Net = Net
        self.usr = None
        self.grid_columnconfigure(0,weight=1)
        self.grid_rowconfigure(1,weight=1)
        self.title = Label(self,text = 'Incoming Messages',width = 18,font = ('Arial',12))
        self.title.place(x=100,y=0)
        self.messages = None
        self.Messcanvas = None
        self.scroll_y = None
        self.dispMess()
        self.img = []

    def changeUser(self,newUser):     #When use is initiated or changed
        if newUser in self.Net.getUsers():
            self.usr = self.Net.getUsers()[newUser]
        self.dispMess()
    def dispMess(self):               #To Display all incoming messages
        if(self.usr is None):
            return
        self.Messcanvas = Canvas(self,height=700,width=600)
        self.scroll_y = Scrollbar(self,orient = 'vertical',command = self.Messcanvas.yview)
        self.messages = Frame(self.Messcanvas)
        r=0
        for mess in self.usr.getRecvMess():
            if (not (mess[0][1] is None or len(mess[0][1]) == 0)):
                self.img.append(ImageTk.PhotoImage(PIL.Image.open(mess[0][1]).resize((600, 400), PIL.Image.ANTIALIAS)))
                #print(self.img[-1])#DEBUG
                if (len(mess) == 4):  # Group Message
                    curr_mess = Label(self.messages,
                                      text=('#%d:From:%s ,Group:%s --Group Message\n%s' % (
                                      r, mess[2], mess[3], mess[0][0])),
                                      image = self.img[-1],
                                      compound = BOTTOM,
                                      font = ('Arial', 16)
                                      )
                    curr_mess.grid(row=r, sticky='snew')

                elif (len(mess) == 3):  # direct Message
                    curr_mess = Label(self.messages,
                                      text=('#%d:From:%s  --Direct Message\n%s' % (r, mess[2], mess[0][0])),
                                      image = self.img[-1],
                                      compound=BOTTOM,
                                      font = ('Arial', 16)
                                      )
                    curr_mess.grid(row=r, sticky='snew')

            else:
                if (len(mess) == 4):  # Group Message
                    curr_mess = Label(self.messages,
                                      text=('#%d:From:%s ,Group:%s --Group Message\n%s' % (
                                      r, mess[2], mess[3], mess[0][0])),
                                      font = ('Arial', 16)
                                      )
                    curr_mess.grid(row=r, sticky='snew')
                elif (len(mess) == 3):  # direct Message
                    curr_mess = Label(self.messages,
                                      text=('#%d:From:%s  --Direct Message\n%s' % (r, mess[2], mess[0][0])),
                                      font = ('Arial', 16)
                                      )
                    curr_mess.grid(row=r, sticky='snew')


            r+=1
        self.Messcanvas.create_window(0, 0, anchor='nw', window=self.messages)
        self.Messcanvas.update_idletasks()
        self.Messcanvas.configure(scrollregion = self.Messcanvas.bbox('all'),
                                  yscrollcommand=self.scroll_y.set)
        self.Messcanvas.place(x=0,y=30)
        self.scroll_y.place(x=600,y=30,relheight=0.935)

# To Display Existing Contacts
class Contacts(Frame):
    def __init__(self, master=None,Net:Network=None):
        Frame.__init__(self, master, bg='#FF8855', height='460', width='250')
        self.master = master
        self.grid(row=1,column=0, sticky="nsew")
        self.label = Label(self, text='Contacts : ', width=25,font = ('Arial',12))
        self.label.grid(row=0, column=0, sticky='nsew')
        self.Net = Net
        self.ContList = Listbox(self,height=20,font = ('Arial',12))
        self.ContList.grid(row=1, column=0, sticky='nsew')
        self.scrollBar = Scrollbar(self)
        self.scrollBar.grid(row=1, column=1, sticky='nsew')
        self.ContList.config(yscrollcommand=self.scrollBar.set)
        self.scrollBar.config(command=self.ContList.yview)
        self.usr = None
    def changeUser(self,newUser):     #When user is changed or initiated
        if newUser in self.Net.getUsers():
            self.usr = self.Net.getUsers()[newUser]
        self.dispCont()
    def dispCont(self):               #Diaplaying all contacts
        if (self.usr is None):
            return
        self.ContList.delete(0, END)
        for usr in self.usr.getContList():
            self.ContList.insert(END, usr.getId())


# To Display Groups
class GrpDisp(Frame):
    def __init__(self, master=None,Net:Network=None):
        Frame.__init__(self, master, bg='#4488FF', height='460', width='250')
        self.master = master
        self.grid(row=2,column=0, sticky="nsew")
        self.label = Label(self, text='Groups : ', width=25,font = ('Arial',12))
        self.label.grid(row=0, column=0, sticky='nsew')
        self.Net = Net
        self.GrpList = Listbox(self,height='20',font = ('Arial',12))
        self.GrpList.grid(row=1,column=0,sticky='nsew')
        self.scrollBar = Scrollbar(self)
        self.scrollBar.grid(row=1,column=1,sticky='nsew')
        self.GrpList.config(yscrollcommand = self.scrollBar.set)
        self.scrollBar.config(command = self.GrpList.yview)
        self.usr = None
    def dispGrp(self):                       #displaying all groups
        if(self.usr is None):
            return
        self.GrpList.delete(0,END)
        for grp in self.usr.getGrpList():
            self.GrpList.insert(END,grp.getId())

    def changeUser(self,newUser):            #when user is changed or initiated
        if newUser in self.Net.getUsers():
            self.usr = self.Net.getUsers()[newUser]
        self.dispGrp()


# To Post Messages
class PostMess(Frame):
    def __init__(self, master=None,Net:Network=None,RecvModule:RecvMess = None):
        Frame.__init__(self, master, bg='#AAAA22', height='250', width='650')
        self.master = master
        self.Net = Net
        self.grid(row=1, column=0, sticky="nsew")
        self.RecvM = RecvModule
        self.toLabel = Label(self,text='To:',font = ('Arial',12))
        self.toLabel.place(x=1,y=3)
        self.toEntry = Text(self,height=1,width=10,font = ('Arial',12))
        self.toEntry.place(x=30,y=3)
        self.selectLabel = Label(self,text='Select Type of Post: ',font = ('Arial',12))
        self.selectLabel.place(x=200,y=5)
        self.selectType = ttk.Combobox(self, height=4, width=20, textvariable=2,font = ('Arial',12),state = 'readonly')
        self.selectType['values'] = ('Grp','Cont')
        self.selectType.place(x = 360,y=5)
        self.selectType.current(0)
        self.sendMessBox = Text(self,height=9,width=44,font = ('Arial',12))
        self.sendMessBox.place(x=0,y=55)
        self.usr = None
        self.post = Button(self,text='Send',font = ('Arial',12),height=2,width=5,command=self.postMess)
        self.post.place(x=500,y=160)
        self.attach = Button(self,text='Attach',font = ('Arial',12),command=self.browse)
        self.attach.place(x=450,y=80)
        self.image = ''
        self.att_status = Label(self,text = 'No Img Attached',width=20,font = ('Arial',12))
        self.att_status.place(x=450,y=40)
        self.rmAttach = Button(self,text='Remove Attachment',font = ('Arial',12),command = self.rm_att)
        self.rmAttach.place(x=450,y=120)
    def browse(self):        #To browse sys to choose image
        flnme = filedialog.askopenfilename(initialdir='/home/anurat',title='Choose a File')
        flnme = shutil.copy(flnme,'./media')
        self.image=flnme
        self.att_status.config(text = 'Img Attached')
    def rm_att(self):        #To remove attachment
        self.image = ''
        self.att_status.config(text='No Img Attached')

    def changeUser(self,newUser):   #When user is initiated or changed
        if newUser in self.Net.getUsers():
            self.usr = self.Net.getUsers()[newUser]
            self.rm_att()
            self.toEntry.delete(1.0,END)
            self.sendMessBox.delete(1.0,END)
    def postMess(self):               #to post the message

        try:
            mess = 'Time: '
            mess += str(datetime.now())
            mess += '\n'
            mess += self.sendMessBox.get(1.0,END)
            to = self.toEntry.get(1.0,END)
            to = to.strip()
            Mess_tp = self.selectType.get()
            if(Mess_tp=='Cont'):
                self.Net.dirMsg(self.usr.getId(),to,mess,self.image,True)
            elif(Mess_tp=='Grp'):
                self.Net.grpMes(self.usr.getId(),to,mess,self.image,True)
            messagebox.showinfo('Succes','Message sent Successfully!')
            self.RecvM.dispMess()
        except:
            messagebox.showerror('Error!No/Invalid User Selected','Please select an User,Or Give valid Recipient')






# Main Window

#Contains incoming messages and send message modules
class RFrame(Frame):
    def __init__(self, master=None,Net:Network = None):
        Frame.__init__(self, master, height="1000", width="650")
        self.master = master
        self.IncMess = RecvMess(self,Net)
        self.PstMess = PostMess(self,Net,self.IncMess)
        self.grid_rowconfigure(0, weight=5)
        self.grid_rowconfigure(1, weight=1)
        self.grid_columnconfigure(0,weight = 1)
        self.grid(row=0, column=1, sticky="nsew")
    def changeUser(self,newUser):
        self.IncMess.changeUser(newUser)
        self.PstMess.changeUser(newUser)


#Contans Contacts,Groups and selectUser
class LFrame(Frame):
    def __init__(self, master=None,Net:Network = None):
        Frame.__init__(self, master, height="1000", width="250")
        self.master = master
        master.grid_columnconfigure(0, weight=1)
        master.grid_columnconfigure(1, weight=4)
        master.grid_rowconfigure(0, weight=1)
        self.usrSelect = UserSelect(self,Net)
        self.Cont = Contacts(self,Net)
        self.Grps = GrpDisp(self,Net)
        self.grid_rowconfigure(0, weight=1)
        self.grid_rowconfigure((1, 2), weight=100)
        self.grid_columnconfigure(0, weight=1)
        self.grid(row=0, column=0, sticky="nsew")
    def changeUser(self,newUser):
        self.Cont.changeUser(newUser)
        self.Grps.changeUser(newUser)
        self.master.changeUser(newUser)


class Window(Frame):
    def __init__(self, master=None):
        Frame.__init__(self, master, height="1000", width="900")
        master.geometry("900x1000")
        Net = Network()
        self.master = master
        self.rightFrame = RFrame(self,Net)
        self.leftFrame = LFrame(self,Net)
        master.grid_columnconfigure(0, weight=1)
        master.grid_rowconfigure(0, weight=1)
        self.grid(column=0, row=0)
    def changeUser(self,newUser):
        self.rightFrame.changeUser(newUser)


root = Tk()
root.config(bg="#AAAAFF")
Img = ImageTk.PhotoImage(PIL.Image.open('./chat.png'))
root.iconphoto(False,Img)
root.title('SimplChat')
app = Window(root)
root.mainloop()

'''
TODO
Things Done
-->Made Skeleton and methods of OOP model  --done
-->Do the GUI Frame Work  --done
-->Use ListBox--done
-->Use ComboBox--done
-->Receiving and sending messages--done
-->Add Extension for sending images--done
-->Improve font size --done
-->Improve Scroll Bar--done
-->Improve Background--done
-->Attach---done
--->Icon--done
'''
