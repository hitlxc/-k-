#基因序列k操作
<br>	

##背景
###用i:S:j表示基因序列S第i到第j位之间的子序列
###i <= j <= s.length
###子序列0:S:i称为前缀，prefix(S,i)
###子序列j:S:S.length称为后缀，suffix(S,j)
###i:S:i为空序列
###j-1:S:j为S中第j个字符Sj

<br>
##k操作
###k(S,a,-1)表示求S序列的前缀prefix(S,a)
###k(S,-1,b)表示求S序列的后缀suffix(S,b)
###k(S,a,b)表示求S序列ab之间的子串i:S:j