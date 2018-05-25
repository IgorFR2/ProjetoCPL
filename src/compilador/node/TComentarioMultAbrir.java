/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilador.node;

import compilador.analysis.*;

@SuppressWarnings("nls")
public final class TComentarioMultAbrir extends Token
{
    public TComentarioMultAbrir(String text)
    {
        setText(text);
    }

    public TComentarioMultAbrir(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TComentarioMultAbrir(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTComentarioMultAbrir(this);
    }
}