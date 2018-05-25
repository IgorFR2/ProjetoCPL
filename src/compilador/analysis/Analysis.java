/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilador.analysis;

import compilador.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProgram(AProgram node);

    void caseTTab(TTab node);
    void caseTEnter(TEnter node);
    void caseTSoma(TSoma node);
    void caseTSubtrair(TSubtrair node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTMod(TMod node);
    void caseTPow(TPow node);
    void caseTGt(TGt node);
    void caseTLt(TLt node);
    void caseTGeq(TGeq node);
    void caseTLeq(TLeq node);
    void caseTParE(TParE node);
    void caseTParD(TParD node);
    void caseTColE(TColE node);
    void caseTColD(TColD node);
    void caseTChaveD(TChaveD node);
    void caseTChaveE(TChaveE node);
    void caseTSemi(TSemi node);
    void caseTCol(TCol node);
    void caseTVirg(TVirg node);
    void caseTUnder(TUnder node);
    void caseTIgual(TIgual node);
    void caseTInteiro(TInteiro node);
    void caseTComentarioLinear(TComentarioLinear node);
    void caseTComentarioMultAbrir(TComentarioMultAbrir node);
    void caseTComentarioMultFechar(TComentarioMultFechar node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}