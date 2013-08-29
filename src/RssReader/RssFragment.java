package RssReader;

import java.util.List;

import com.ngapham.englishlearningkit.R;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class RssFragment extends Fragment {
	
	private static ListView list;
	private View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null)
		{
			view = inflater.inflate(R.layout.fragment_layout, container, false);
			list = (ListView) view.findViewById(R.id.listArticle);
			startService();
			openArticle();
		} else {
			ViewGroup parent = (ViewGroup) view.getParent();
			parent.removeView(view);
		}
		return view;
	}

	private void startService() {
		Intent intent = new Intent(getActivity(), RssService.class);
        intent.putExtra(RssService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
	}
	
	private final ResultReceiver resultReceiver = new ResultReceiver( new Handler())
	{
		@SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            List<Article> articles = (List<Article>) resultData.getSerializable(RssService.ARTICLES);
            if (articles != null) {
                RssAdapter adapter = new RssAdapter(getActivity(), articles);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occured while downloading the rss feed.", Toast.LENGTH_LONG).show();
            }
          
            list.setVisibility(View.VISIBLE);
        };
	};
	
	public void openArticle()
	{
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long getID) {
				RssAdapter adapter = (RssAdapter) parent.getAdapter();
				Article currentArticle = adapter.getItem(position);
				String urlLink = currentArticle.getLink();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(urlLink));
				startActivity(i);
			}
		});
	}

}
