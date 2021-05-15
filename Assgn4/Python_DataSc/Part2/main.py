#Imports
from tqdm import tqdm
import os
from math import floor
import matplotlib.pyplot as plt
from my_package.model import ObjectDetectionModel
from my_package.data import Dataset
from my_package.analysis import show_boxes
from my_package.data.transforms import FlipImage, RescaleImage, BlurImage, CropImage, RotateImage
from PIL import Image
import numpy as np
def experiment(annotation_file, detector, transforms, outputs):
    '''
        Function to perform the desired experiments

        Arguments:
        annotation_file: Path to annotation file
        detector: The object detector
        transforms: List of transformation classes
        outputs: path of the output folder to store the images
    '''
    #create op_directory if not created
    if(not os.path.isdir(outputs)):
        os.mkdir(outputs)
    if(not outputs.endswith('/')):
        outputs+='/'
    all_imgs = os.path.join(outputs,'All Images')
    if(not os.path.isdir(all_imgs)):
        os.mkdir(all_imgs)
    if(not all_imgs.endswith('/')): all_imgs+='/'
    #Create the instance of the dataset.
    dataset = Dataset(annotation_file,[])

    #Iterate over all data items.
    j=0
    print('Getting the Predictions...')
    for data in tqdm(dataset):
        shp = data['image'].shape
        img = np.uint8(np.transpose(data['image'],(1,2,0)) * 255)
        img = Image.fromarray(img)
        #Get the predictions from the detector.
        boxes = detector(data['image'])
        gt_boxes = []
        for i in range(len(boxes[0])):
            coord ,category,score = boxes[0][i],boxes[1][i],boxes[2][i]
            gt_boxes.append([category,coord[0][0],coord[0][1],coord[1][0],coord[1][1]])
        #Draw the boxes on the image and save them.
        op_img = show_boxes(img,gt_boxes)
        svg_path = all_imgs+str(j)+'.png'
        op_img.save(svg_path)
        j+=1

    #Do the required analysis experiments.
    print('Doing the Analysis Part...')

    img = Image.fromarray(np.uint8(np.transpose(dataset[1]['image'],(1,2,0)) * 255))#original image numbered 1
    horiz_flip = FlipImage()(img)
    blurred = BlurImage()(img)
    twice_rescaled = RescaleImage((img.size[0]*2,img.size[1]*2))(img)
    half_rescaled = RescaleImage((floor(img.size[0] * 0.5), floor(img.size[1] * 0.5)))(img)
    rot_45 = RotateImage(45)(img)
    rot_90 = RotateImage(90)(img)
    ip_imgl = [img,horiz_flip,blurred,twice_rescaled,half_rescaled,rot_45,rot_90]
    op_imgl = []
    for c_img in tqdm(ip_imgl):
        boxes = detector(np.transpose(np.array(c_img), (2, 1, 0)) / 255)  # detect boxes
        gt_boxes = []
        for i in range(len(boxes[0])):
            coord, category, score = boxes[0][i], boxes[1][i], boxes[2][i]
            gt_boxes.append([category, coord[0][0], coord[0][1], coord[1][0], coord[1][1]])
        op_imgl.append(show_boxes(c_img, gt_boxes))  #add to output image list

    fg,axes = plt.subplots(nrows = 3,ncols=3,figsize = (10,10))
    k = 0
    for i in range(3):
        for j in range(3):
            axes[i,j].imshow(op_imgl[k])
            k+=1
            if(k==len(op_imgl)):    break
        if (k == len(op_imgl)):    break

    plt.subplots_adjust(wspace=0, hspace=0)
    plt.show()
    analysis_dir = os.path.join(outputs,'Analysis')
    if(not analysis_dir.endswith('/')):
        analysis_dir+='/'

    if(not os.path.isdir(analysis_dir)):
        os.mkdir(analysis_dir)
    i = 0
    for c_img in op_imgl:
        c_img.save(analysis_dir + str(i)+'.png')
        i+=1

def main():
    detector = ObjectDetectionModel()
    experiment('./data/annotations.jsonl', detector, [], './op_imgs/') # Sample arguments to call experiment()

if __name__ == '__main__':
    main()