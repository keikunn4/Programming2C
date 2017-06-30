inport numpy as np

inf =math.inf()#後で修正
map = np.array([
	[inf ,21, 7, 13, 15],
	[11 ,inf , 19, 12, 25],
	[15, 24, inf, 13, 5],
	[6, 17, 9, inf, 22],
	[28, 6, 11, 5, inf]
	])

S_col = 0
S_row = 0
G = 0

for i in range(5):
	S_row += map[i,:].max()
	map[i,:] -= map[i,:].max()

for i in range(5):
	S_col += map[:,i].max()
	map[:,i] -= map[:,i].max()
	

#R/*
map <- t(matrix(c(Inf ,21, 7, 13, 15, 11 ,Inf , 19, 12, 25, 15, 24, Inf, 13, 5, 6, 17, 9, Inf, 22, 28, 6, 11, 5, Inf),ncol=5,nrow=5))
map

index=1:5
min=Inf
arg_min = c()
for(i in index){
for(j in index[-c(i)]){
for(k in index[-c(i,j)]){
for(l in index[-c(i,j,k)]){
for(m in index[-c(i,j,k,l)]){
sum =map[i,j]+map[j,k]+map[k,l]+map[l,m]+map[m,i]
if(sum < min){ min = sum;  arg_min=c(i,j,k,l,m)}
}
}
}
}
}
min
arg_min

*/
