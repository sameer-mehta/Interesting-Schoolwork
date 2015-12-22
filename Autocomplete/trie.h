#include <stdio.h>
#include <stdlib.h>

//trie.h
//Created by Sameer Mehta
typedef struct Trie {
	char letter;						//every index when storing nodes/characters
	char * term;						//word that is entered or added to trie
	struct Trie * upper;					//parent node
	struct Trie * lower[54];				//child node, can go to 54 times(a-zA-z and apostrophe)
}trie;

//prototypes of the functions used in trie.c file
void TrieInitialize(trie ** node);
void TrieAdd(trie * node, char * word);
trie * TrieSearch(trie * node, char * prefix);
void TriePrinter(trie * node);
