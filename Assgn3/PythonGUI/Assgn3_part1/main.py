#19CS10071
#Anurat Bhattacharya
from tkinter import *
from tkinter import messagebox
import numpy as np
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg,NavigationToolbar2Tk)

#Left Frame for entering Expression
class lFrame(Frame):
    def __init__(self, master=None, rFrame=None):
        Frame.__init__(self, master,height=master.winfo_height(),width=master.winfo_width()*1/5)
        self.master = master
        self.rFrame = rFrame
        self.grid_columnconfigure(0,weight=2)
        self.grid_columnconfigure(1,weight=3)
        self.grid_rowconfigure((0,1,2),weight=1)
        self.pack(side = LEFT)

        #adding labels and textboxes for input
        self.label1 = Label(self, text="Expression(in variable x): ")
        self.label1.grid(row=0, column=0, sticky="SNEW")

        self.exprtxt = Text(self, width=25, height=5, padx=4, pady=4, bd=6)
        self.exprtxt.insert(END, "Enter The one variable Expression here")
        self.exprtxt.grid(row=0, column=1, sticky="SNEW")

        self.label2 = Label(self, text="Left range for evaluation: ")
        self.label2.grid(row=1, column=0, sticky="SNEW")

        self.lrange = Text(self, width=10, height=5, padx=2, pady=2, bd=1)
        self.lrange.insert(END, "The Left Range")
        self.lrange.grid(row=1, column=1, sticky="SNEW")

        self.label3 = Label(self, text="Right range for evaluation: ")
        self.label3.grid(row=2, column=0, sticky="SNEW")

        self.rrange = Text(self, width=10, height=5, padx=2, pady=2, bd=1)
        self.rrange.insert(END, "The Right Range")
        self.rrange.grid(row=2, column=1, sticky="SNEW")

        self.pltButton = Button(self, text="Plot", command=self.plot, width=10, height=2, padx=2, pady=20)
        self.pltButton.grid(row=3, column=0, sticky="SNEW")


        self.exitButton = Button(self, text="Exit", command=exit, width=4, height=2, padx=2, pady=20)
        self.exitButton.grid(row=3, column=1, sticky="SNEW")

    #function to plot in the right frame
    def plot(self):
        expr = self.exprtxt.get(1.0, END)
        try:
            lr = float(self.lrange.get(1.0, END).strip('\n'))
            rr = float(self.rrange.get(1.0, END).strip('\n'))
        except:
            messagebox.showerror(title="Invalid Ranges", message="Enter Valid Range")
        X = []
        Y = []
        for x in np.linspace(lr, rr, 1000):
            expr = expr.strip('\n')
            try:
                y = eval(expr)
            except:
                messagebox.showerror(title="Invalid Expression", message="Enter Valid Expression")

            X.append(x)
            Y.append(y)
        self.rFrame.plot(X,Y)

#right Frame for Plotting
class rFrame(Frame):
    def __init__(self, master=None):
        Frame.__init__(self, master)
        self.master = master
        self.pack(side=RIGHT)
        #initializing a default graph with (0,0) plotted
        self.fig = Figure(figsize=(10,10),dpi=80)
        self.plt = self.fig.add_subplot(111)
        self.plt.plot(0)
        self.plt.set_title("Y vs X Graph")
        self.plt.set_xlabel("X")
        self.plt.set_ylabel("Y")
        self.canvas = FigureCanvasTkAgg(self.fig, master=self)
        self.canvas.draw()
        self.canvas.get_tk_widget().pack(side=RIGHT)
        toolbar = NavigationToolbar2Tk(self.canvas, self.master)
        toolbar.update()
        self.canvas.get_tk_widget().pack(side=RIGHT)

    #function to use matplotlib backend to plot the graph obtained as a list of points
    def plot(self,x,y):
        self.plt.clear()
        self.plt.plot(x,y)
        self.plt.set_title("Y vs X Graph")
        self.plt.set_xlabel("X")
        self.plt.set_ylabel("Y")
        self.canvas.draw()
        self.canvas.get_tk_widget().pack(side=RIGHT)
        toolbar = NavigationToolbar2Tk(self.canvas, self.master)#to add navigation panel
        toolbar.update()
        self.canvas.get_tk_widget().pack(side=RIGHT)


#root window
class Window(Frame):
    def __init__(self, master=None):
        Frame.__init__(self, master)
        self.master = master
        master.geometry("1200x800")
        self.rightFrame = rFrame(master)
        self.leftFrame = lFrame(master, self.rightFrame)
        master.wm_title("Plotter")
        self.pack()



root = Tk()
app = Window(root)
root.mainloop()
