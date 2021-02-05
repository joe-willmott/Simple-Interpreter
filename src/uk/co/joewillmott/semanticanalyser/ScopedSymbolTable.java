package uk.co.joewillmott.semanticanalyser;

import uk.co.joewillmott.semanticanalyser.symbol.Symbol;

import java.util.HashMap;

public class ScopedSymbolTable extends HashMap<String, Symbol> {
    private String name;
    private int scopeLevel;
    private boolean functionScope;
    private ScopedSymbolTable enclosingScope;
    private ScopedSymbolTable globalScope;

    public ScopedSymbolTable(String name, int scopeLevel, boolean functionScope, ScopedSymbolTable enclosingScope, ScopedSymbolTable globalScope) {
        this.name = name;
        this.scopeLevel = scopeLevel;
        this.functionScope = functionScope;
        this.enclosingScope = enclosingScope;
        this.globalScope = globalScope;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }

    public boolean isFunctionScope() {
        return functionScope;
    }

    public ScopedSymbolTable getGlobalScope() {
        return globalScope;
    }

    public void insert(Symbol symbol) {
        this.put(symbol.getName(), symbol);
    }

    public Symbol lookup(String name, boolean currentScopeOnly) {
        Symbol symbol = this.get(name);

        if (symbol != null) {
            return symbol;
        }

        if (currentScopeOnly || this.enclosingScope == null || this.functionScope) {
            if (this.globalScope == this || this.globalScope == null) {
                return null;
            }

            return this.globalScope.lookup(name, true);
        }

        return this.enclosingScope.lookup(name, false);
    }
}
