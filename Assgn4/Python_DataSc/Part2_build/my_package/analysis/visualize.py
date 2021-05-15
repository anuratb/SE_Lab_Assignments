# Imports
from PIL import Image, ImageDraw, ImageFont

font = ImageFont.truetype('Arimo-Bold.ttf', 18)


def plot_boxes(sample, gt_boxes):  # Write the required arguments

    # The function should plot the predicted boxes on the images and save them.
    # Tip: keep the dimensions of the output image less than 800 to avoid RAM crashes.
    i = 0
    image = sample.copy()
    edit = ImageDraw.Draw(image)
    for box in gt_boxes:
        edit.rectangle([(box[1], box[2]), (box[3], box[4])], outline='red')
        if(box[2]>=23):
            edit.text((box[1], box[2] - 20), box[0], 'red', font=font)
        else:
            edit.text((box[1], box[2]), box[0], 'red', font=font)

        i += 1
        if (i == 5):
            break
    return image
