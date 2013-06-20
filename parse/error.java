package parse;
public class error
{
	String[] err=
    {
        "null"                  // 0
        ,"missing lparen"       // 1
        ,"illegal: ':='"        // 2
        ,"illegal character behind '=' " // 3
        ,"missing '=' "                  // 4
        ,"illegal character "            // 5
        ,"missing ';' "                  // 6
        ,"illegal prase "                // 7
        ,"integer overflow "             // 8
        ,"integer "                      // 9
        ,"missing rparen"                // 10
        ,"missing ';'"                   // 11
        ,"ident not exist"               // 12
        ,"ident is not a variable"       // 13
        ,"missing '='"                   // 14
        ,"missing ident after call"      // 15
        ,"missing proc after call"       // 16
        ,"missing then"                  // 17
        ,"missing end"                   // 18
        ,"missing do"                    // 19
        ,"indent too long"               // 20
        ,"missing operator"              // 21
        ,"indent is a proc name"         // 22
        ,"missing rparen"                // 23
        ,"Head error"                    // 24
        ,"Const error"                   // 25
        ,"Var error"                     // 26
        ,"Procedure error"               // 27
        ,"Statement error"               // 28
        ,"Not defined"                   // 29
        ,"const can not be assigned a value" // 30
    };
	error(int linenumber,int t)
	{
		System.out.println("row:"+linenumber+"  error number"+t+"  "+err[t]);
	}
}