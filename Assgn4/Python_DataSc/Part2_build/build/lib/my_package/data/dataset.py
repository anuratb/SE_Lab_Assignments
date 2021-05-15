#Imports
import json
import numpy as np
from PIL import Image
from pathlib import Path


class Dataset(object):
    '''
        A class for the dataset that will return data items as per the given index
    '''

    def __init__(self, annotation_file, transforms = None):
        '''
            Arguments:
            annotation_file: path to the annotation file
            transforms: list of transforms (class instances)
                        For instance, [<class 'RandomCrop'>, <class 'Rotate'>]
        '''
        f = open(annotation_file,'r')
        self.dir = str(Path(annotation_file).parent)
        lines = f.read().split('\n')
        self.data = []
        for line in lines:
            if(len(line)>0):
                self.data.append(json.loads(line))
        self.transforms = transforms

        

    def __len__(self):
        '''
            return the number of data points in the dataset
        '''
        return len(self.data)

        

    def __getitem__(self, idx):
        '''
            return the dataset element for the index: "idx"
            Arguments:
                idx: index of the data element.

            Returns: A dictionary with:
                image: image (in the form of a numpy array) (shape: (3, H, W))
                gt_bboxes: N X 5 array where N is the number of bounding boxes, each 
                            consisting of [class, x1, y1, x2, y2]
                            x1 and x2 lie between 0 and width of the image,
                            y1 and y2 lie between 0 and height of the image.

            You need to do the following, 
            1. Extract the correct annotation using the idx provided.
            2. Read the image and convert it into a numpy array (wont be necessary
                with some libraries). The shape of the array would be (3, H, W).
            3. Scale the values in the array to be with [0, 1].
            4. Create a dictonary with both the image and annotations
            4. Perform the desired transformations.
            5. Return the transformed image and annotations as specified.
        '''
        curr_data = self.data[idx]
        curr_img_pth = curr_data['img_fn']
        curr_img = Image.open(self.dir+'/'+curr_img_pth)   #as PIL Image
        gt_bboxes = [ ([c_dict['category']]+c_dict['bbox']) for c_dict in curr_data['bboxes']]
        #print(type(curr_img))
        #ret_img = np.array(curr_img)
        #print(type(ret_img))
        for transform in self.transforms:
            curr_img = transform(curr_img)
        #print(type(curr_img))
        ret_img = np.array(curr_img)
        #print(ret_img)
        #print(type(ret_img))
        #print(ret_img)
        ret_img = ret_img/255
        ret_img = np.transpose(ret_img,(2,0,1))
        ret_dict = {}
        ret_dict['image'] = ret_img
        ret_dict['gt_boxes']= gt_bboxes
        return ret_dict



        