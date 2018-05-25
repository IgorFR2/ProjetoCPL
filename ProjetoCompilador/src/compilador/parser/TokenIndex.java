/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilador.parser;

import compilador.node.*;
import compilador.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTTab(@SuppressWarnings("unused") TTab node)
    {
        this.index = 0;
    }

    @Override
    public void caseTEnter(@SuppressWarnings("unused") TEnter node)
    {
        this.index = 1;
    }

    @Override
    public void caseTSoma(@SuppressWarnings("unused") TSoma node)
    {
        this.index = 2;
    }

    @Override
    public void caseTSubtrair(@SuppressWarnings("unused") TSubtrair node)
    {
        this.index = 3;
    }

    @Override
    public void caseTMult(@SuppressWarnings("unused") TMult node)
    {
        this.index = 4;
    }

    @Override
    public void caseTDiv(@SuppressWarnings("unused") TDiv node)
    {
        this.index = 5;
    }

    @Override
    public void caseTMod(@SuppressWarnings("unused") TMod node)
    {
        this.index = 6;
    }

    @Override
    public void caseTPow(@SuppressWarnings("unused") TPow node)
    {
        this.index = 7;
    }

    @Override
    public void caseTGt(@SuppressWarnings("unused") TGt node)
    {
        this.index = 8;
    }

    @Override
    public void caseTLt(@SuppressWarnings("unused") TLt node)
    {
        this.index = 9;
    }

    @Override
    public void caseTGeq(@SuppressWarnings("unused") TGeq node)
    {
        this.index = 10;
    }

    @Override
    public void caseTLeq(@SuppressWarnings("unused") TLeq node)
    {
        this.index = 11;
    }

    @Override
    public void caseTParE(@SuppressWarnings("unused") TParE node)
    {
        this.index = 12;
    }

    @Override
    public void caseTParD(@SuppressWarnings("unused") TParD node)
    {
        this.index = 13;
    }

    @Override
    public void caseTColE(@SuppressWarnings("unused") TColE node)
    {
        this.index = 14;
    }

    @Override
    public void caseTColD(@SuppressWarnings("unused") TColD node)
    {
        this.index = 15;
    }

    @Override
    public void caseTChaveD(@SuppressWarnings("unused") TChaveD node)
    {
        this.index = 16;
    }

    @Override
    public void caseTChaveE(@SuppressWarnings("unused") TChaveE node)
    {
        this.index = 17;
    }

    @Override
    public void caseTSemi(@SuppressWarnings("unused") TSemi node)
    {
        this.index = 18;
    }

    @Override
    public void caseTCol(@SuppressWarnings("unused") TCol node)
    {
        this.index = 19;
    }

    @Override
    public void caseTVirg(@SuppressWarnings("unused") TVirg node)
    {
        this.index = 20;
    }

    @Override
    public void caseTUnder(@SuppressWarnings("unused") TUnder node)
    {
        this.index = 21;
    }

    @Override
    public void caseTIgual(@SuppressWarnings("unused") TIgual node)
    {
        this.index = 22;
    }

    @Override
    public void caseTInteiro(@SuppressWarnings("unused") TInteiro node)
    {
        this.index = 23;
    }

    @Override
    public void caseTComentarioLinear(@SuppressWarnings("unused") TComentarioLinear node)
    {
        this.index = 24;
    }

    @Override
    public void caseTComentarioMultAbrir(@SuppressWarnings("unused") TComentarioMultAbrir node)
    {
        this.index = 25;
    }

    @Override
    public void caseTComentarioMultFechar(@SuppressWarnings("unused") TComentarioMultFechar node)
    {
        this.index = 26;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 27;
    }
}