import pylab
import math
import imageio


filename = "C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\【HOI4】巴巴罗萨DLC（No Step Back) 中文预告片 - 1.英文版熟肉(Av293655529,P1).mp4"


vid = imageio.get_reader(filename,  'ffmpeg')

for im in enumerate(vid):


    fig = pylab.figure()

    fig.suptitle('image #{}'.format(num), fontsize=20)

    pylab.imshow(image)

pylab.show()
