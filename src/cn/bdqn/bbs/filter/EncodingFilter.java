package cn.bdqn.bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encode;//�ַ�����
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode=filterConfig.getInitParameter("encode");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(null==request.getCharacterEncoding()){
			request.setCharacterEncoding(encode);
		}
		chain.doFilter(request, response);//���У���������Ŀ�ĵأ�
	}

	@Override
	public void destroy() {
		encode=null;
	}

}
