/* Auto-generated Model Implementation for Ecore2XMI */
#include <Ecore2XMI.h>
#include <Ecore2XMI.tab.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern FILE *yyin;
extern int yydebug;

int yyparse (void);

int main(int argc, char *argv[]) {
	yydebug = 0;
    fprintf(stderr, "Starting %d\n", argc);
	if (argc > 0) {
		yyin = fopen(*++argv, "r");
}
  int status = yyparse();
     fprintf(stderr, "yyparse() => %d\n", status);
  if (status == 0)
     fprintf(stderr, "Successful parsing.\n");
  else
     fprintf(stderr, "error found.\n");
}

void addChild(void ***out, void *in) {
	return;
}

void addToList(void ***out, void *in) {
	return;
}

void addRoot(const YYSTYPE *in) {
	return;
}

extern int yylex(void);
extern void yyless(int);
extern int yyleng;

int ns_ecore = -1;
int ns_xmi = -1;
int ns_xmlns = -1;
int ns_xsi = -1;

/*void beginXMLNSAnalysis() {
	struct yy_buffer_state *yyXMLNSbuffer = yy_create_buffer(stdin, sizeof(yy_buffer_state));
    tt_push_buffer(yyXMLNSbuffer);
	int getCount = 0;
	int c = -1;
	while (c != GT) {
		c = yylex(); getCount++;
//	    int yytoken = YYTRANSLATE (c);
//		yy_symbol_print (stderr, yytype, &yylval);
		if (c == KWxmlns) {
			fprintf(stderr, "Got KWxmlns\n");
			c = yylex(); getCount++;
			if (c == COLON) {
				c = yylex(); getCount++;
				if (c == IDENTIFIER) {
					char *p1 = malloc(yyleng+1);
					strncpy(p1, yylval.identifier, yyleng);
					p1[yyleng] = '\0';
					c = yylex(); getCount++;
					if (c == EQ) {
						c = yylex(); getCount++;
						if (c == STRING) {
							char *p2 = malloc(yyleng+1);
							strncpy(p2, yylval.identifier, yyleng);
							p2[yyleng] = '\0';
							fprintf(stderr, "xmlns %s => %s\n", p1, p2);
						}
					}
				}
			}
			else if (c == EQ) {
				c = yylex(); getCount++;
				if (c == STRING) {
					char *p3 = malloc(yyleng+1);
					strncpy(p3, yylval.identifier, yyleng);
					p3[yyleng] = '\0';
					fprintf(stderr, "xmlns => %s\n", p3);
				}
			}
		}
	}
//	yyless(getCount);
}*/

void copyRTTI(const char *ruleName, YYSTYPE *out, const YYSTYPE *in) {
	fprintf(stderr, "%s : copyRTTI\n", ruleName);
//	out->yysenum = in->yysenum;
	out->pointer = in->pointer;
}

void createRTTI(const char *ruleName, YYSTYPE *out, const struct rtti *rtti) {
	fprintf(stderr, "%s : createRTTI %s\n", ruleName, rtti->className);
//	out->yysenum = rtti->yysenum;
	out->pointer = malloc(rtti->size);
}

extern struct xmlns xmlnsData[];

void gatherXMLNS(const char *nsPrefix, const char *nsURI) {
	fprintf(stderr, "gatherXMLNS %s\n", nsURI);
	for (struct xmlns *xmlns = xmlnsData; xmlns->uri != 0; xmlns++) {
		if (strcmp(xmlns->uri, nsURI) == 0) {
			xmlns->identifier = nsPrefix;
			fprintf(stderr, "gatherXMLNS %s => %d\n", nsURI, xmlns->token);
			break;
		}
	}
}

boolean getBoolean(const char *string) {
	fprintf(stderr, "getBoolean %s\n", string);
	return strcmp(string, "true") == 0;
}

int getInt(const char *string) {
	fprintf(stderr, "getInt %s\n", string);
	return atoi(string);
}

const char *getString(const char *string) {
	fprintf(stderr, "getString %s\n", string);
	return string;
}

void *resolveXmiId(const char *string) {
	fprintf(stderr, "resolveXmiId %s\n", string);
	return string;
}

void setXMIVersion(const char *version) {
	fprintf(stderr, "setXMIVersion %s\n", version);
}

void setXMLEncoding(const char *encoding) {
	fprintf(stderr, "setXMLEncoding %s\n", encoding);
}

void setXMLNS(const char *name, const char *namespace) {
	fprintf(stderr, "setXMLNS %s => %s\n", name, namespace);
}

void setXMLVersion(const char *version) {
	fprintf(stderr, "setXMLVersion %s\n", version);
}

int yyerror(char *s) {
	fprintf(stderr, "%s\n", s);
	return 0;
}

struct savedToken
{
	int token;
	const char *text;
};

struct savedToken savedTokens[256] = {0};

int savedTokenWriteIndex = 0;
int savedTokenReadIndex = -1;

void saveIdentifier(const char *identifier) {
	savedTokens[savedTokenWriteIndex].token = IDENTIFIER;
	savedTokens[savedTokenWriteIndex].text = identifier;
	savedTokenWriteIndex++;
}

void saveString(const char *string) {
	savedTokens[savedTokenWriteIndex].token = STRING;
	savedTokens[savedTokenWriteIndex].text = string;
	savedTokenWriteIndex++;
}

void saveToken(int token) {
	const char *text = tokenText(token);
	savedTokens[savedTokenWriteIndex].token = token;
	savedTokens[savedTokenWriteIndex].text = null;
	savedTokenWriteIndex++;
}

void endXMLNSanalysis() {
//	assert savedTokenWriteIndex >= 0;
	savedTokenReadIndex = 0;
}

extern char *yytext;

int yylex(void) {
	int c;
	if (savedTokenReadIndex >= 0) {
		if (savedTokenReadIndex >= savedTokenWriteIndex) {
			savedTokenReadIndex = -1;
			savedTokenWriteIndex = -1;
			c = yylex_impl();
		}
		else {
			struct savedToken *p = &savedTokens[savedTokenReadIndex++];
			c = p->token;
			if ((c == IDENTIFIER) || (c == STRING)) {
				yytext = (char *)p->text;
			}
			else {
				yytext = (char *)tokenText(c);
			}
			yyleng = strlen(yytext);
		}
	}
	else {
		c = yylex_impl();
	}
	fprintf(stderr, "yylex : %d => %d: %s\n", c, yyleng, yytext);
	fflush(stderr);
	if (c == IDENTIFIER) {
		for (struct xmlns *xmlns = xmlnsData; xmlns->uri != 0; xmlns++) {
			const char *id = xmlns->identifier;
			if ((id != null) && (strcmp(id, yytext) == 0)) {
				c = xmlns->token;;
				fprintf(stderr, "re-yylex : %d => %d: %s\n", c, yyleng, yytext);
				fflush(stderr);
				break;
			}
		}
	}
	return c;
}

char *mystrdup(char *p) {
	char *q = strdup(p);
	return q;
}
