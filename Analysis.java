package SyntaxAnalysis;
import java.util.*;

public class Analysis {
	//优先关系表 1表示大于，10表示无效，-1表示小于，0表示相等
	int[][] priority = {{1,-1,-1,-1,-1,-1,-1,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,1,10,10,10,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{10,10,10,10,10,10,10,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,10,1,1,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,10,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,-1,-1,-1,-1,10,1,1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,-1,-1,10,1,10},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,10,10,10,10,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,10,10,10,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,10,10,10,-1,10,1,1},
			{1,10,10,10,10,10,10,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,10,10,10,10,10,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,1,1,10,10,-1,1,1},
			{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,10,0,10},
			{1,1,1,1,1,1,1,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,10,1,1,1,10,10,10,1,1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,10,10,0}};
	String[] operator = { ",", "=", "+=", "-=", "*=", "/=","%=","?", ":", "||", "&&", "!",
			"!=", "==", "<=", "<", ">=", ">", "|", "^", "&", ">>", "<<", "+", "-", "*", "/", "%", "~", "(",
			")",  "++", "--", "a", "i" ,"[", "]", "#"};
	String[] syntax = { "N,N", "N=N", "N+=N", "N-=N", "N*=N", "N/=N", "N%=N","N?N:N", "N||N", "N&&N",
			"!N", "N!=N", "N==N", "N<=N", "N<N", "N>=N", "N>N", "N|N", "N^N", "N&N", "N>>N", "N<<N", "N+N", "N-N", "N*N",
			"N/N", "N%N", "~N", "-N", "(N)", "++N", "--N", "N++", "N--", "a", "i", "iN", "[N]"};
	String grammer_display = "(0)A->A,B (1)A->B (2)A->C (3)A->D (4)B->O=C (5)B->O+=C (6)B->O-=C "
			             + "\n(7)B->O*=C (8)B->O/=C (9)B->O%=C (10)C->D?C:C (11)C->H (12)D->D||E "
			             + "\n(13)D->E (14)E->E&&F (15)E->F (16)F->!F (17)F->G (18)G->H!=H "
			             + "\n(19)G->H==H (20)G->H<=H (21)G->H<H (22)G->H>=H (23)G->H>H (24)G->(D) "
			             + "\n(25)H->H|I (26)H->I (27)I->I^J (28)I->J (29)J->J&K (30)J->K "
			             + "\n(31)K->K>>L (32)K->K<<L (33)K->L (34)L->L+M (35)L->L-M (36)L->M "
			             + "\n(37)M->M*N (38)M->M/N (39)M->M%N (40)M->N (41)N->~N (42)N->-N "
			             + "\n(43)N->(A) (44)N->++O (45)N->--O (46)N->O++ (47)N->O-- (48)N->O "
			             + "\n(49)N->a (50)O->i (51)O->iP (52)P->[H]";
	boolean flag = true;

	HashMap<String,Integer> hash_operator = new HashMap<>();
	HashMap<String,String> hash_variable = new HashMap<>();
	Stack<String> value = new Stack<>();
	
	public Analysis() {
		for(int i = 0;i<38;i++) {  
			hash_operator.put(operator[i], i);
		}
	}
	//语法分析 
	String[] analysis(String str) {
		String[] result = {"",""};
		initialize_calculate(str);
		//形成一个语句数组
		String[] str_array = str.split(";");
		String stack = new String("#");
		//当前的符号和栈顶符号
		String now,S;
		int i = 0,j = 0,last = 0,k = 0,r = 0;
		//将不是赋值语句的加入str
		for(i = 0;i<=str_array.length;i++) {
			if(i == str_array.length) {
				return result;
			}
			if(!str_array[i].contains("int")&&!str_array[i].contains("float")) {
				str = str_array[i];
				break;
			}
		}
		String str_copy = str;
		//预处理
		str_copy = str_copy.replace(" ", "");
		str_copy = str_copy.replace("\n", "");
		str_copy = str_copy.replace("\r", "");
		str = initialize_reduction(str);
		//表头信息
		result[0] = "符号栈\t当前符号\t剩余符号串\t移近或规约";
		//字符串的末尾加上#以便分析
		str += "#";
		//比较栈顶和当前元素的优先级
		for(i = 0;i<str.length();i++) {
			if(i == str.length()-1||!judge_2operator(str.charAt(i), str.charAt(i+1))) {
				now = str.substring(i, i+1);
			}
			else now = str.substring(i, i+2);
		    while(true) {
		    	last = stack.length()-1;
		    	while(last>=0&&stack.charAt(last)=='N') {
		    		last--;
		    	}
		    	if(last == 0 ||!judge_2operator(stack.charAt(last-1), stack.charAt(last))) {
		    		S = stack.substring(last, last+1);
		    	}
		    	else S = stack.substring(last-1 , last+1);

		    	if(S.equals("#") && now.equals("#")) {
		    		result[0] +="\n"+stack+"\t"+now+"\t"+str.substring(i+now.length())+"\t"+"接受";
		    		result[1] = value.pop();
		    		return result;
		    	}
				//比较优先级。
		    	if(compare_priority(S, now)!=1 ) {
		    		result[0] +="\n"+stack+"\t"+now+"\t"+str.substring(i+now.length())+"\t"+"移进";
		    		stack += now;
		    		i += now.length()-1;
		    		if(now.equals("a")) {
		    			for(r = k;r<str_copy.length()&&(Character.isDigit(str_copy.charAt(r))||str_copy.charAt(r) == '.');r++);
		    			value.push(str_copy.substring(k,r));
		    			k = r;
		    		}
		    		else if(now.equals("i")) {
		    			for(r = k;r<str_copy.length()&&(Character.isDigit(str_copy.charAt(r))||Character.isAlphabetic(str_copy.charAt(r))||str_copy.charAt(r) == '_');r++);
		    			value.push(str_copy.substring(k,r));
		    			k = r;
		    		}
		    		else k += now.length();
		    		break;
		    	}
				//读到三元表达式需要特殊处理
		    	if(S.equals(":")) {
					//循环找到？的位置
		    		for(j = last;j>=1;j--) {
		    			if(stack.charAt(j) == '?') {
		    				break;
		    			}
		    		}
		    		j--;
		    		j--;
		    	}
				//处理括号匹配问题
		    	else if(S.equals(")")) {
		    		for(j = last;j>=0;j--) {
		    			if(stack.charAt(j) == '(') {
		    				break;
		    			}
		    		}
		    		j--;
		    	}
		    	else if(S.equals("]")) {
		    		for(j = last;j>=0;j--) {
		    			if(stack.charAt(j) == '[') {
		    				break;
		    			}
		    		}
		    		j--;
		    	}
				//找到形如 < > 组成的符号串组合，以便后续规约
		    	else for(j = last;j>=0;j--) {
		    		String tmp;
		    		while(j>=0&&stack.charAt(j)=='N') {
		    			j--;
		    		}
		    		if(j == 0 ||!judge_2operator(stack.charAt(j-1), stack.charAt(j))) {
		    			tmp = stack.substring(j, j+1);
		    		}
		    		else tmp = stack.substring(j-1 , j+1);
		    		if(compare_priority(tmp, S)==-1) {
		    			break;
		    		}
		    		j -= tmp.length()-1;
		    	}
		    	String tmp;
		    	tmp = stack.substring(j+1);
				//通过 find_syntax 函数找到与子串匹配的文法规则编号。
		    	int find = find_syntax(tmp);
				//找到匹配的 则进行规约，规约完的部分替换为N
		    	if(find != -1) {
		    		result[0] +="\n"+stack+"\t"+now+"\t"+str.substring(i+now.length())+"\t"+"归约"+"("+find+")";
		    		calculate(find);
		    		stack = stack.substring(0,j+1) + "N";
		    	} 
		    	else {
					//出错更新
		    		result[0] +="\n"+stack+"\t"+now+"\t"+str.substring(i+now.length())+"\t"+"错误";
		    		result[1] = "表达式不合法！";
		    		flag = false;
		    		return result;
		    	}
		    }
		}
		return result;
	}
	
	int find_syntax(String str) {
		for(int i = 0; i< syntax.length; i++) {
			if(str.equals(syntax[i]))
				return i;
		}
		return -1;
	}
	
	int compare_priority(String s1,String s2) {
		int t1 = hash_operator.getOrDefault(s1, -1);
		int t2 = hash_operator.getOrDefault(s2, -1);
		return priority[t1][t2];
	}
	
	boolean judge_2operator(char tmp1,char tmp2) {
		if(tmp2 == '='&&(tmp1 == '+'||tmp1 == '-'||tmp1 == '*'||tmp1 == '/'||tmp1 == '%'||tmp1 == '='
				||tmp1 == '!'||tmp1 == '<'||tmp1 == '>')) {
			return true; 
		}
		else if(tmp1 == tmp2&&(tmp1 == '+'||tmp1 == '-'||tmp1 == '|'||tmp1 == '&'||tmp1 == '<'||tmp1 == '>')) {
			return true;
		}
		else return false;
	}
	
	String initialize_reduction(String str) {
		str = str.replace(" ", "");
		str = str.replace("\n", "");
		str = str.replace("\r", "");
		char tmp;
		//用于分割字符串的指针 分别标记变量名的开始和结束
		int low = 0,high = 0;
		for(int i = 0;i<str.length();i++) {
			tmp = str.charAt(i);
			//读取字符
			if(Character.isAlphabetic(tmp)||tmp == '_') {
				//变量名的开头 其中可以是_开头
				low = i;
				for(i = i+1;i<str.length();i++) {
					tmp = str.charAt(i);
					//说明一个变量名到了结尾
					if(!Character.isAlphabetic(tmp)&&!Character.isDigit(tmp)&&tmp!='_') {
						break;
					}
				}
				//将原先变量名替换为i
				high = i;
                str = str.substring(0, low) + 'i' + str.substring(high);
                i = low+1;
			}
			//读取数字
			else if(Character.isDigit(tmp)) {
				low = i;
				high = i+1;
				for(i = i+1;i<str.length();i++) {
					tmp = str.charAt(i);
					if(!Character.isDigit(tmp)&&tmp!='.') {
						break;
					}
				}
				//将数字替换成a
				high = i;
				str = str.substring(0, low) + 'a' + str.substring(high);
				i = low + 1;
			}
		}
		return str;
	}
	// 将每个输入语句分割
	void initialize_calculate(String str) {
		String[] str_array = str.split(";");
		int low = 0,high = 0;
		char tmp;
		String name,value;
		for(int j = 0;j<str_array.length;j++) {
			//判断是否是int定义语句
			if(str_array[j].contains("int")) {
				str_array[j] = str_array[j].replace("int", "");
				str_array[j] = str_array[j].replace(" ", "");
				str_array[j] = str_array[j].replace("\n", "");
				str_array[j] = str_array[j].replace("\r", "");
				for(int i = 0;i<str_array[j].length();i++) {
					if(Character.isAlphabetic(str_array[j].charAt(i))||str_array[j].charAt(i) == '_') {
						low = i;
						for(i = i+1;i<str_array[j].length();i++) {							
							tmp = str_array[j].charAt(i);
							if(!Character.isAlphabetic(tmp)&&!Character.isDigit(tmp)&&tmp!='_') {
								break;
							}
						}
						high = i;
						name = str_array[j].substring(low, high);
					}
					else continue;
					//是否是数组 放到hash中
                    if(i<str_array[j].length()&&i<str_array[j].length()&&str_array[j].charAt(i) == '[') {
						while(i<str_array[j].length()&&str_array[j].charAt(i) != ']') {
							i++;
						}
						if(i<str_array[j].length()&&str_array[j].charAt(i+1) == '=') {
							int r = 0;
							for(;i<str_array[j].length()&&str_array[j].charAt(i)!='}';i++) {
								if(!Character.isDigit(str_array[j].charAt(i))) continue;
								int k = i;
								for(;Character.isDigit(str_array[j].charAt(k));k++);
								hash_variable.put(name+'['+ r +']', str_array[j].substring(i, k));
								i = k-1;
								r++;
							}
						}
					}
					//如果有赋值操作也要存到hash中
					if(i<str_array[j].length()&&str_array[j].charAt(i) == '=') {
						i++;
						if(Character.isDigit(str_array[j].charAt(i))) {
							low = i;
							for(i = i+1;i<str_array[j].length();i++) {								
								tmp = str_array[j].charAt(i);
								if(!Character.isDigit(tmp)) {
									break;
								}
							}
							high = i;
							value = str_array[j].substring(low, high);
							hash_variable.put(name, value);
						}
					}
				}
			}
			//判断是否是float语句 和int同理
			else if(str_array[j].contains("float")) {
				str_array[j] = str_array[j].replace("float", "");
				str_array[j] = str_array[j].replace(" ", "");
				str_array[j] = str_array[j].replace("\n", "");
				str_array[j] = str_array[j].replace("\r", "");
				for(int i = 0;i<str_array[j].length();i++) {
					if(Character.isAlphabetic(str_array[j].charAt(i))||str_array[j].charAt(i) == '_') {
						low = i;
						for(i = i+1;i<str_array[j].length();i++) {							
							tmp = str_array[j].charAt(i);
							if(!Character.isAlphabetic(tmp)&&!Character.isDigit(tmp)&&tmp!='_') {
								break;
							}
						}
						high = i;
						name = str_array[j].substring(low, high);
					}
					else continue;
					if(i<str_array[j].length()&&str_array[j].charAt(i) == '[') {
						while(i<str_array[j].length()&&str_array[j].charAt(i) != ']') {
							i++;
						}
						if(i<str_array[j].length()&&str_array[j].charAt(i+1) == '=') {
							int r = 0;
							for(;i<str_array[j].length()&&str_array[j].charAt(i)!='}';i++) {
								if(!Character.isDigit(str_array[j].charAt(i))&&str_array[j].charAt(i)!='.') continue;
								int k = i;
								for(;Character.isDigit(str_array[j].charAt(k))||str_array[j].charAt(i)=='.';k++);
								value = str_array[j].substring(i, k);
								if(!value.contains(".")) {
									value += '.';
								}
								hash_variable.put(name+'['+ r +']',value);
								i = k-1;
								r++;
							}
						}
					}
					else if(i<str_array[j].length()&&str_array[j].charAt(i) == '=') {
						i++;
						if(Character.isDigit(str_array[j].charAt(i))) {
							low = i;
							for(i = i+1;i<str_array[j].length();i++) {
								tmp = str_array[j].charAt(i);
								if(!Character.isDigit(tmp)&&tmp!='.') {
									break;
								}
							}
							high = i;
							value = str_array[j].substring(low, high);
							if(!value.contains(".")) {
								value += '.';
							}
							hash_variable.put(name,value);
						}
					}
				}
			}
			else break;
		}
	}
	//将变量名转换为实际的变量值
	String variable_to_valve(String str) {
		char first = str.charAt(0);
		//如果是变量名，可以通过HashMap的方法得到数值，未找到则默认为0
		if(Character.isAlphabetic(first)||first == '_') {
			return hash_variable.getOrDefault(str, "0");
		}
		return str;
	}
	
	void calculate(int gram) {
		switch(gram) {
		    case 0: calculate_1(',');break;
		    case 1: calculate_1('=');break;
			case 2: calculate_1('+');break;
			case 3: calculate_1('-');break;
			case 4: calculate_1('*');break;
			case 5: calculate_1('/');break;
			case 6: calculate_1('%');break;
			case 7:calculate_2();break;
			case 8: calculate_3('|');break;
			case 9: calculate_3('&');break;
			case 10: calculate_5('!');break;
			case 11: calculate_3('!');break;
			case 12: calculate_3('=');break;
			case 13: calculate_3('{');break;
			case 14: calculate_3('<');break;
			case 15: calculate_3('}');break;
			case 16: calculate_3('>');break;
			case 17: calculate_4('|');break;
			case 18: calculate_4('^');break;
			case 19: calculate_4('&');break;
			case 20: calculate_4('>');break;
			case 21: calculate_4('<');break;
			case 22: calculate_4('+');break;
			case 23: calculate_4('-');break;
			case 24: calculate_4('*');break;
			case 25: calculate_4('/');break;
			case 26: calculate_4('%');break;
			case 27: calculate_5('~');break;
			case 28: calculate_5('_');break; // 负号
			case 29: calculate_5('(');break;
			case 30: calculate_5('+');break;
			case 31: calculate_5('-');break;
			case 32: calculate_5('+');break;
			case 33: calculate_5('-');break;
		    case 34: calculate_5('a');break;
		    case 35: calculate_5('i');break;
		    case 36: calculate_5('[');break;
		    case 37: calculate_5(']');break;
		}
	}
	//进行基本的加减乘除运算
	void calculate_1(char model) {
		//获得栈顶前两个元素
		String str2 = value.pop();
		String str1 = value.pop();
		//转换为相应的数值
		str2 = variable_to_valve(str2);
		String tmp = variable_to_valve(str1);
		switch(model) {
			//操作数入栈
		    case ',':
		    	value.push(str2);
		    	break;
		    case '=':
				//赋值后入栈
		    	hash_variable.put(str1, str2);
		    	value.push(str2);
		    	break;
		    case '+': 
		    	if(str1.contains(".")||str2.contains(".")) {
		    		float ans = Float.parseFloat(tmp)+Float.parseFloat(str2);
		    	    tmp = String.valueOf(ans);
		    		
		    	}
		    	else {
		    		int ans = Integer.parseInt(tmp)+Integer.parseInt(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	hash_variable.put(str1, tmp);
	    		value.push(tmp);
		    	break;
		    case '-':
		    	if(str1.contains(".")||str2.contains(".")) {
		    		float ans = Float.parseFloat(tmp)-Float.parseFloat(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	else {
		    		int ans = Integer.parseInt(tmp)-Integer.parseInt(str2);
		    		tmp = String.valueOf(ans);		    		
		    	}
		    	hash_variable.put(str1, tmp);
		    	value.push(tmp);
		    	break;
		    case '*': 
		    	if(str1.contains(".")||str2.contains(".")) {
		    		float ans = Float.parseFloat(tmp)*Float.parseFloat(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	else {
		    		int ans = Integer.parseInt(tmp)*Integer.parseInt(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	hash_variable.put(str1, tmp);
	    		value.push(tmp);
		    	break;
		    case '/': 
		    	if(str1.contains(".")||str2.contains(".")) {
		    		float ans = Float.parseFloat(tmp)/Float.parseFloat(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	else {
		    		int ans = Integer.parseInt(tmp)/Integer.parseInt(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	hash_variable.put(str1, tmp);
	    		value.push(tmp);
		    	break;
		    case '%': 
		    	if(str1.contains(".")||str2.contains(".")) {
		    		float ans = Float.parseFloat(tmp)%Float.parseFloat(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	else {
		    		int ans = Integer.parseInt(tmp)%Integer.parseInt(str2);
		    		tmp = String.valueOf(ans);
		    	}
		    	hash_variable.put(str1, tmp);
	    		value.push(tmp);
		    	break;
		}
		
	}
	//三元表达式
	void calculate_2() {
		String str3 = value.pop();
		String str2 = value.pop();
		String str1 = value.pop();
		str2 = variable_to_valve(str2);
		str3 = variable_to_valve(str3);
		if(str1.equals("1")) value.push(str2);
		else value.push(str3);
	}
	//逻辑运算
	void calculate_3(char model) {
		String str2 = value.pop();
		String str1 = value.pop();
		str1 = variable_to_valve(str1);
		str2 = variable_to_valve(str2);
		String tmp = "0";
		switch(model) {
		case '|':
			if(str1.equals("1")||str2.equals("1")) value.push("1");
			else value.push("0");
			break;
		case '&':
			if(str1.equals("1")&&str2.equals("1")) value.push("1");
			else value.push("0");
			break;
		case '!':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) != Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) != Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		case '=':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) == Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) == Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		case '<':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) < Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) < Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		case '{':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) <= Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) <= Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		case '>':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) > Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) > Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		case '}':
			if(str1.contains(".")||str2.contains(".")) {
	    		if(Float.parseFloat(str1) >= Float.parseFloat(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
	    	else {
	    		if(Integer.parseInt(str1) >= Integer.parseInt(str2)) tmp = "1";
	    		else tmp = "0";
	    	}
			value.push(tmp);
			break;
		}
	}
	
	void calculate_4(char model) {
		String str2 = value.pop();
		String str1 = value.pop();
		str1 = variable_to_valve(str1);
		str2 = variable_to_valve(str2);
		switch(model) {
		case '|': 
	    	if(!str1.contains(".")&& !str2.contains(".")) {
	    	    int ans = Integer.parseInt(str1)|Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
		case '^': 
	    	if(!str1.contains(".")&& !str2.contains(".")) {
	    	    int ans = Integer.parseInt(str1)^Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
		case '&': 
	    	if(!str1.contains(".")&& !str2.contains(".")) {
	    	    int ans = Integer.parseInt(str1)&Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
		case '>': 
	    	if(!str1.contains(".")&& !str2.contains(".")) {
	    	    int ans = Integer.parseInt(str1)>>Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
		case '<': 
	    	if(!str1.contains(".")&& !str2.contains(".")) {
	    	    int ans = Integer.parseInt(str1)<<Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
	    case '+': 
	    	if(str1.contains(".")||str2.contains(".")) {
	    		float ans = Float.parseFloat(str1)+Float.parseFloat(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	else {
	    		int ans = Integer.parseInt(str1)+Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
	    case '-':
	    	if(str1.contains(".")||str2.contains(".")) {
	    		float ans = Float.parseFloat(str1)-Float.parseFloat(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	else {
	    		int ans = Integer.parseInt(str1)-Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
	    case '*': 
	    	if(str1.contains(".")||str2.contains(".")) {
	    		float ans = Float.parseFloat(str1)*Float.parseFloat(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	else {
	    		int ans = Integer.parseInt(str1)*Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
	    case '/': 
	    	if(str1.contains(".")||str2.contains(".")) {
	    		float ans = Float.parseFloat(str1)/Float.parseFloat(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	else {
	    		int ans = Integer.parseInt(str1)/Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
	    case '%': 
	    	if(str1.contains(".")||str2.contains(".")) {
	    		float ans = Float.parseFloat(str1)%Float.parseFloat(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	else {
	    		int ans = Integer.parseInt(str1)%Integer.parseInt(str2);
	    		value.push(String.valueOf(ans));
	    	}
	    	break;
		}
	}
	
	void calculate_5(char model) {
		String str = value.pop();
		String tmp = variable_to_valve(str);
		switch(model) {
		case '!':
			str = variable_to_valve(str);
			if(str.equals("0")) value.push("1");
			else value.push("0");
			break;
		case '~':
			str = variable_to_valve(str);
			if(!str.contains(".")) {
				int ans = ~Integer.parseInt(str);
				value.push(String.valueOf(ans));
			}
			break;
		case '_':
			str = variable_to_valve(str);
			if(str.contains(".")) {
				float ans = -Float.parseFloat(str);
				value.push(String.valueOf(ans));
			}
			else {
				int ans = -Integer.parseInt(str);
				value.push(String.valueOf(ans));
			}
			break;
		case '(':
			value.push(str);
			break;
		case '+':
			if(tmp.contains(".")) {
	    		float ans = Float.parseFloat(tmp)+1;
	    	    tmp = String.valueOf(ans);
	    	}
	    	else {
	    		int ans = Integer.parseInt(tmp)+1;
	    		tmp = String.valueOf(ans);
	    	}
	    	hash_variable.put(str, tmp);
    		value.push(tmp);
	    	break;
		case '-':
			if(tmp.contains(".")) {
	    		float ans = Float.parseFloat(tmp)-1;
	    	    tmp = String.valueOf(ans);
	    	}
	    	else {
	    		int ans = Integer.parseInt(tmp)-1;
	    		tmp = String.valueOf(ans);
	    	}
	    	hash_variable.put(str, tmp);
    		value.push(tmp);
			break;
		case 'a':
			value.push(str);
			break;
		case 'i':
			value.push(str);
			break;
		case '[':
			String str1 = value.pop();
			value.push(hash_variable.getOrDefault(str1+str, "0"));
			break;
		case ']':
			value.push('['+str+']');
			break;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Analysis f = new Analysis();
//		String str = "int a=10,b=20,c;c+=c";
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		String[] ans = f.analysis(str);
		System.out.println(ans[0]+"  "+ans[1]);
	}

}
