import java.util.*;

public class Calculator {
	private boolean isOperator(String token) {
		// token が演算子 +, -, *, / のいずれかならば true を返すプログラムを以下に書きます。
		if(token.equals("+")|token.equals("-")|token.equals("*")|token.equals("/")){return true;}
		else{return false;}
	}

	private boolean isNumber(String token) {
		// token が数値ならば true を返すプログラムを以下に書きます。
		try{
			double	value	=	Double.parseDouble(token);
			return true;
		}
		catch(NumberFormatException e){return false;}
	}

	public double getAnswer(String equation) {
		Stack stack = new Stack();
		StringTokenizer st = new StringTokenizer(equation);

		while (st.hasMoreElements()) {
			String token = st.nextToken();
			if(isNumber(token)){
				stack.push(token);
			}
			if(isOperator(token)){
				if(token.equals("+")){
					double val2 = Double.parseDouble(stack.pop());
					double val1 = Double.parseDouble(stack.pop());
					double val0 = val1+val2;
					stack.push(	String.valueOf(val0) );
				}else if(token.equals("-")){
					double val2 = Double.parseDouble(stack.pop());
					double val1 = Double.parseDouble(stack.pop());
					double val0 = val1-val2;
					stack.push(	String.valueOf(val0) );
				}else if(token.equals("*")){
					double val2 = Double.parseDouble(stack.pop());
					double val1 = Double.parseDouble(stack.pop());
					double val0 = val1*val2;
					stack.push(	String.valueOf(val0) );
				}else if(token.equals("/")){
					double val2 = Double.parseDouble(stack.pop());
					double val1 = Double.parseDouble(stack.pop());
					double val0 = val1/val2;
					stack.push(	String.valueOf(val0) );
				}
			}
			// token に数字や演算子が一つずつ順番に代入されていきます。
			// 演算子なら式を評価した結果を，数値ならその値が 
			// stack の先頭に格納されるプログラムを以下に書きます.
		}
		
		// stack の先頭を数字に変換して返します。
		double	value	=	Double.parseDouble(stack.pop());
		//System.out.println(value);
		return value;
	}

	// 答えだけ書く人は、この関数は削除する。
	public String getEquation(String equation) {
		Stack stack = new Stack();
		StringTokenizer st = new StringTokenizer(equation);

		while (st.hasMoreElements()) {
			String token = st.nextToken();
			
			// token に数字や演算子が一つずつ順番に代入されていきます。
			// それらを処理して、中置記法の数式が stack の先頭に格納されるよ
			// うなプログラムを以下に書きます.
			
			if(isNumber(token)){
					stack.push(token);
			}
			if(isOperator(token)){
				String val2 = stack.pop();
				String val1 = stack.pop();
				stack.push( "("+val1+token+val2+")" );
			}
		}
		String value = stack.pop();
		return value;
	}
}