package RssReader;

import java.util.List;

import com.ngapham.englishlearningkit.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RssAdapter extends BaseAdapter {
	
	private final List<Article> articles;
	private final Context context;

	public RssAdapter(Context context, List<Article> articles) {
		this.articles = articles;
		this.context = context;
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = View.inflate(context, R.layout.article, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(articles.get(position).getTitle());
		return convertView;
	}
	
	static class ViewHolder {
		TextView title;
	}

}
