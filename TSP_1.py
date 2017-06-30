inport numpy as np

inf =math.inf()#後で修正
map = np.array([
	[inf ,21, 7, 13, 15],
	[11 ,inf , 19, 12, 25],
	[15, 24, inf, 13, 5],
	[6, 17, 9, inf, 22],
	[28, 6, 11, 5, inf]
	])

for i in range(5):
	map[i,:] -= map[i,:].max

